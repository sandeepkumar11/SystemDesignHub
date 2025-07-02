package model;

import enums.PackageSize;

public class Package {
    private final int packageId;
    private final PackageSize packageSize;
    private boolean isDelivered;

    public Package(int packageId, PackageSize packageSize) {
        this.packageId = packageId;
        this.packageSize = packageSize;
        this.isDelivered = false;
    }

    public int getPackageId() {
        return packageId;
    }

    public PackageSize getPackageSize() {
        return packageSize;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }
}
