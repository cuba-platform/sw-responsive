package org.strangeway.responsive.web.components.impl.cssextension;

import com.vaadin.shared.JavaScriptExtensionState;

import java.util.HashMap;
import java.util.Map;

public class CustomCssAttributesExtensionState extends JavaScriptExtensionState {
    public Map<String, String> css = new HashMap<>();
}