public class ShipmentFlowDemo {

    public static void main(String[] args) {

        DeliveryTracker tracker = new DeliveryTracker();

        tracker.recordStage("Order Packed");
        tracker.recordStage("Dispatched");
        tracker.recordStage("On Route");
        tracker.recordStage("Successfully Delivered");

        tracker.displayTimeline();

        tracker.insertCheckpointAfter("Dispatched", "Customs Clearance");
        tracker.displayTimeline();

        tracker.flagAsLost();
        tracker.displayTimeline();
    }
}
