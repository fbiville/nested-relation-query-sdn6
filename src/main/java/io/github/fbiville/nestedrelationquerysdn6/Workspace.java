package io.github.fbiville.nestedrelationquerysdn6;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashMap;
import java.util.Map;

@Node(primaryLabel = "Workspace")
public class Workspace extends HypercubeNode {

    @Property("STATE")
    private String state;

    @Relationship(type = "WORKSPACE_IN")
    private Map<Work, WorkspaceIn> workspaceIn = new HashMap<>();

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Map<Work, WorkspaceIn> getWorkspaceIn() {
        return workspaceIn;
    }

    public void setWorkspaceIn(Map<Work, WorkspaceIn> workspaceIn) {
        this.workspaceIn = workspaceIn;
    }
}
