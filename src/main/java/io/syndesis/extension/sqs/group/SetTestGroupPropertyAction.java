package io.syndesis.extension.sqs.group;

import io.syndesis.extension.api.Step;
import io.syndesis.extension.api.annotations.Action;
import io.syndesis.extension.api.annotations.ConfigurationProperty;
import org.apache.camel.CamelContext;
import org.apache.camel.model.ProcessorDefinition;

import java.util.Map;
import java.util.Optional;

import static org.apache.camel.language.simple.SimpleLanguage.simple;

@Action(id = "setGroupId", name = "Set SQS GroupId", description = "Sets the groupId for the SQS connector", tags = { "extension", "sqs" })
public class SetTestGroupPropertyAction implements Step {
    @ConfigurationProperty(
            name = "groupId",
            displayName = "Group ID",
            description = "Group ID for the messages sent to the FIFO queue",
            required = true
    )
    private String groupId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public Optional<ProcessorDefinition<?>> configure(CamelContext camelContext, ProcessorDefinition<?> processorDefinition, Map<String, Object> map) {
        processorDefinition.setProperty("CamelAwsMessageGroupId", simple(groupId));
        return Optional.empty();
    }
}
