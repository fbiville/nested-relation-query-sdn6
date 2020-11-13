package io.github.fbiville.nestedrelationquerysdn6;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashMap;
import java.util.Map;

@Node(primaryLabel = "Changeset")
public class Changeset extends HypercubeNode {

    @Property("USER")
    private String user;

    @Relationship(type = "CHANGESET_IN")
    private Map<Workspace, ChangesetIn> changesetIn = new HashMap<>();

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Map<Workspace, ChangesetIn> getChangesetIn() {
        return changesetIn;
    }

    public void setChangesetIn(Map<Workspace, ChangesetIn> changesetIn) {
        this.changesetIn = changesetIn;
    }
}
