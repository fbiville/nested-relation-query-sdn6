package io.github.fbiville.nestedrelationquerysdn6;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import java.util.ArrayList;
import java.util.List;

@Node(primaryLabel = "Workspace")
public class Workspace extends HypercubeNode {

    @Relationship(type = "WORKSPACE_IN", direction = Direction.OUTGOING)
    private List<WorkspaceIn> workspaceIns = new ArrayList<>();

	public List<WorkspaceIn> getWorkspaceIns() {
		return workspaceIns;
	}

	public void setWorkspaceIns(List<WorkspaceIn> workspaceIns) {
        this.workspaceIns = workspaceIns;
    }
}
