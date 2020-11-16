package io.github.fbiville.nestedrelationquerysdn6;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import java.util.ArrayList;
import java.util.List;

@Node(primaryLabel = "Changeset")
public class Changeset extends HypercubeNode {

    @Property("USER")
    private String user;

    @Relationship(type = "CHANGESET_IN", direction = Direction.OUTGOING)
    private List<ChangesetIn> changesetIns = new ArrayList<>();

    public List<ChangesetIn> getChangesetIns() {
		return changesetIns;
	}

	public void setWorkspaces(List<ChangesetIn> changesetIns) {
		this.changesetIns = changesetIns;
	}

	public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
