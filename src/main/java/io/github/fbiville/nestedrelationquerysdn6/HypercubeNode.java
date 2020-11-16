package io.github.fbiville.nestedrelationquerysdn6;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node(labels = "Hypercube")
public abstract class HypercubeNode {

    @Id
    @GeneratedValue
    private Long internalID;

    @Property("ID")
    private Long id;

    @Property("CANCELED")
    private boolean canceled;

    @Property("STATE")
    private String state;

    public Long getInternalID() {
        return internalID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
