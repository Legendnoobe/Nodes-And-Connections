package ConnectedNodes;

public class Connection {
    public int length;
    public final String connectionName;
    public final Node connectedTo;
    public Boolean active = true;

    public Connection(String connectionName, Node connectedTo,int length) {

        this.connectionName = connectionName;
        this.connectedTo = connectedTo;
        this.length = length;
    }

    public Connection(String connectionName, Node connectedTo) {
        this.connectionName = connectionName;
        this.connectedTo = connectedTo;
    }
}

