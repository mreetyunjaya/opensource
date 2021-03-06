package io.cscanner.core.test.engine;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public interface ScannerCoreFactory {
    ScannerCore create(List<RuleConfiguration> rules);
}
