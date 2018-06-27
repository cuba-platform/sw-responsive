package org.strangeway.responsive.web.components;

public interface HasMinMaxDimensions {
    void setMinHeight(String minHeight);
    String getMinHeight();

    void setMaxHeight(String maxHeight);
    String getMaxHeight();

    void setMinWidth(String minWidth);
    String getMinWidth();

    void setMaxWidth(String maxWidth);
    String getMaxWidth();
}