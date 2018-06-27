package org.strangeway.responsive.web.components.impl.cssextension;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractClientConnector;
import com.vaadin.server.AbstractJavaScriptExtension;
import com.vaadin.shared.communication.SharedState;

@JavaScript("vaadin://cssextension/custom-css-attributes.js")
public class CustomCssAttributesExtension extends AbstractJavaScriptExtension {
    public CustomCssAttributesExtension(AbstractClientConnector target) {
        super(target);
    }

    public void set(String attribute, String cssValue) {
        getState().css.put(attribute, cssValue);
    }

    public String get(String attribute) {
        return getState(false).css.get(attribute);
    }

    @Override
    protected CustomCssAttributesExtensionState getState() {
        return (CustomCssAttributesExtensionState) super.getState();
    }

    @Override
    protected CustomCssAttributesExtensionState getState(boolean markAsDirty) {
        return (CustomCssAttributesExtensionState) super.getState(markAsDirty);
    }
}