package command.center.model;

public enum CampaignStatus {
    ACTIVE("ACTIVE"),INACTIVE("INACTIVE"),DELETE("DELETE");

    private  String name;

    CampaignStatus(String active) {
        this.name = active;
    }

    @Override
    public String toString() {
        return name;
    }
}
