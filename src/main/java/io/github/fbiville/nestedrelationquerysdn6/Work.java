package io.github.fbiville.nestedrelationquerysdn6;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node(primaryLabel = "Work")
public class Work extends HypercubeNode {

    @Property("STATE")
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
