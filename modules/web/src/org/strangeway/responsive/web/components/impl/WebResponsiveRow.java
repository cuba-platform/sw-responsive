/*
 * Copyright (c) 2008-2018 StrangeWay.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.strangeway.responsive.web.components.impl;

import com.haulmont.cuba.web.gui.components.WebAbstractOrderedLayout;
import com.haulmont.cuba.web.gui.components.WebWrapperUtils;
import com.jarektoro.responsivelayout.ResponsiveColumn;
import com.jarektoro.responsivelayout.ResponsiveRow;
import org.strangeway.responsive.web.components.ResponsiveLayout;

public class WebResponsiveRow extends WebAbstractOrderedLayout<ResponsiveRow>
        implements ResponsiveLayout.Row {
    public WebResponsiveRow() {
        component = new com.jarektoro.responsivelayout.ResponsiveRow();
    }

    @Override
    public void setExpandRatio(ResponsiveLayout.Column responsiveColumn,
                               ResponsiveLayout.DisplaySize displaySize, int width) {
        component.setExpandRatio(responsiveColumn.unwrap(ResponsiveColumn.class),
                com.jarektoro.responsivelayout.ResponsiveLayout.DisplaySize.valueOf(displaySize.name()),
                width);
    }

    @Override
    public void setVerticalSpacing(ResponsiveLayout.SpacingSize spacingSize, boolean verticalSpacing) {
        component.setVerticalSpacing(ResponsiveRow.SpacingSize.valueOf(spacingSize.name()), verticalSpacing);
    }

    @Override
    public void setHorizontalSpacing(ResponsiveLayout.SpacingSize spacingSize, boolean horizontalSpacing) {
        component.setHorizontalSpacing(ResponsiveRow.SpacingSize.valueOf(spacingSize.name()), horizontalSpacing);
    }

    @Override
    public void setVerticalSpacing(boolean verticalSpacing) {
        component.setVerticalSpacing(verticalSpacing);
    }

    @Override
    public void setHorizontalSpacing(boolean horizontalSpacing) {
        component.setHorizontalSpacing(horizontalSpacing);
    }

    @Override
    public void setDefaultComponentAlignment(Alignment defaultAlignment) {
        component.setDefaultComponentAlignment(WebWrapperUtils.toVaadinAlignment(alignment));
    }

    @Override
    public void setMargin(ResponsiveLayout.MarginSize marginSize, ResponsiveLayout.DisplaySize displaySize) {
        component.setMargin(
                ResponsiveRow.MarginSize.valueOf(marginSize.name()),
                com.jarektoro.responsivelayout.ResponsiveLayout.DisplaySize.valueOf(displaySize.name())
        );
    }

    @Override
    public void setMargin(boolean margin) {
        component.setMargin(margin);
    }

    @Override
    public void setGrow(boolean grow) {
        component.setGrow(grow);
    }

    @Override
    public void setShrink(boolean shrink) {
        component.setShrink(shrink);
    }
}