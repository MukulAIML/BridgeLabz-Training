public class DeliveryTracker {

    private StatusNode start;
    private StatusNode end;

    public void recordStage(String label) {
        StatusNode node = new StatusNode(label);

        if (start == null) {
            start = end = node;
        } else {
            end.next = node;
            end = node;
        }
    }

    public boolean insertCheckpointAfter(String target, String checkpoint) {
        StatusNode current = start;

        while (current != null) {
            if (current.label.equals(target)) {
                StatusNode node = new StatusNode(checkpoint);
                node.next = current.next;
                current.next = node;

                if (current == end) {
                    end = node;
                }
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void flagAsLost() {
        start = null;
        end = null;
    }

    public void displayTimeline() {
        if (start == null) {
            System.out.println("Tracking unavailable (shipment lost or not initialized).");
            return;
        }

        StatusNode current = start;
        while (current != null) {
            System.out.print(current.label);
            if (current.next != null) {
                System.out.print(" => ");
            }
            current = current.next;
        }
        System.out.println();
    }
}
