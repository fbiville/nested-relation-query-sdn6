package io.github.fbiville.nestedrelationquerysdn6;

import org.assertj.core.util.Maps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataNeo4jTest
class NestedRelationQuerySdn6ApplicationTests {

    @Autowired
    private Driver driver;

    @Container
    private static final Neo4jContainer<?> neo4jContainer = new Neo4jContainer<>("neo4j:4.0");

    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.neo4j.uri", neo4jContainer::getBoltUrl);
        registry.add("spring.neo4j.authentication.username", () -> "neo4j");
        registry.add("spring.neo4j.authentication.password", neo4jContainer::getAdminPassword);
    }

    @BeforeEach
    void prepare() {
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> {
                tx.run("MATCH (n) DETACH DELETE n");
                tx.run("CREATE (:Changeset:Hypercube {USER: 'some-user'})-[:CHANGESET_IN]->(ws:Workspace {STATE: 'some-state'}), " +
                        "     (ws)-[:WORKSPACE_IN]->(:Hypercube:Work {STATE: 'some-other-state'})");
                return null;
            });
        }
    }

    @Test
    void finds_changeset_by_id(@Autowired ChangesetRepository repository) {
        Long changesetId = findChangesetId("some-user");

        Optional<Changeset> result = repository.findById(changesetId);

        assertThat(result).hasValueSatisfying((changeset) -> {
            assertThat(changeset.getId()).isEqualTo(changesetId);
            assertThat(changeset.getUser()).isEqualTo("some-user");
            Map<Workspace, ChangesetIn> changesetRels = changeset.getChangesetIn();
            assertThat(changesetRels).hasSize(1);
            Workspace workspace = changesetRels.keySet().iterator().next();
            assertThat(workspace.getState()).isEqualTo("some-state");
            Map<Work, WorkspaceIn> workspaceRels = workspace.getWorkspaceIn();
            assertThat(workspaceRels).hasSize(1);
            Work work = workspaceRels.keySet().iterator().next();
            assertThat(work.getState()).isEqualTo("some-other-state");
        });
    }

    private Long findChangesetId(String user) {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> {
                Result result = tx.run(
                        "MATCH (c:Changeset:Hypercube {USER: $user}) RETURN id(c) AS id",
                        Maps.newHashMap("user", user));
                return result.single().get("id").asLong();
            });
        }
    }

}
