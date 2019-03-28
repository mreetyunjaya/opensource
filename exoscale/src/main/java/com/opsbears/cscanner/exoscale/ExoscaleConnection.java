package com.opsbears.cscanner.exoscale;

import com.opsbears.cscanner.core.CloudProviderConnection;
import com.opsbears.cscanner.firewall.FirewallClient;
import com.opsbears.cscanner.firewall.FirewallConnection;
import com.opsbears.cscanner.s3.S3Connection;
import com.opsbears.cscanner.s3.S3Factory;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ExoscaleConnection implements CloudProviderConnection, S3Connection, FirewallConnection {
    private final String name;
    private final ExoscaleConfiguration exoscaleConfiguration;

    public ExoscaleConnection(
        String name,
        ExoscaleConfiguration exoscaleConfiguration
    ) {
        this.name = name;

        this.exoscaleConfiguration = exoscaleConfiguration;
    }

    @Override
    public S3Factory getS3Factory() {
        return new ExoscaleS3ClientSupplier(exoscaleConfiguration);
    }

    @Override
    public String getConnectionName() {
        return name;
    }

    @Override
    public FirewallClient getFirewallClient() {
        //todo handle cloudstack config
        return new ExoscaleFirewallClient(
            exoscaleConfiguration.key,
            exoscaleConfiguration.secret
        );
    }
}
