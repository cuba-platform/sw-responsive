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

package org.strangeway.responsive.web.components.xml;

import com.haulmont.bali.util.Dom4j;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Component.Alignment;
import com.haulmont.cuba.gui.xml.layout.loaders.ContainerLoader;
import org.dom4j.Element;
import org.strangeway.responsive.web.components.ResponsiveLayout;
import org.strangeway.responsive.web.components.ResponsiveLayout.ContainerType;
import org.strangeway.responsive.web.components.ResponsiveLayout.ContentAlignment;
import org.strangeway.responsive.web.components.ResponsiveLayout.DisplaySize;
import org.strangeway.responsive.web.components.ResponsiveLayout.SpacingSize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

public class ResponsiveLayoutLoader extends ContainerLoader<ResponsiveLayout> {
    protected List<PendingRow> pendingRows = Collections.emptyList();
    protected List<PendingColumn> pendingColumns = Collections.emptyList();

    @Override
    public void createComponent() {
        resultComponent = factory.createComponent(ResponsiveLayout.class);
        loadId(resultComponent, element);

        List<Element> rowElements = Dom4j.elements(element, "row");
        for (Element rowElement : rowElements) {
            if (pendingRows.isEmpty()) {
                pendingRows = new ArrayList<>();
            }

            ResponsiveLayout.Row row = factory.createComponent(ResponsiveLayout.Row.class);
            loadId(row, rowElement);
            resultComponent.addRow(row);

            pendingRows.add(new PendingRow(row, rowElement));

            List<Element> colElements = Dom4j.elements(rowElement, "column");
            for (Element columnElement : colElements) {
                if (pendingColumns.isEmpty()) {
                    pendingColumns = new ArrayList<>();
                }

                ResponsiveLayout.Column column = factory.createComponent(ResponsiveLayout.Column.class);
                loadId(column, columnElement);
                row.addColumn(column);

                pendingColumns.add(new PendingColumn(column, columnElement));

                Element contentElement = columnElement.element("content");
                if (contentElement != null) {
                    createSubComponents(column, contentElement);
                }
            }
        }
    }

    @Override
    public void loadComponent() {
        assignFrame(resultComponent);

        loadAlign(resultComponent, element);
        loadCaption(resultComponent, element);
        loadDescription(resultComponent, element);

        loadWidth(resultComponent, element);
        loadHeight(resultComponent, element);
        loadStyleName(resultComponent, element);
        loadResponsive(resultComponent, element);
        loadIcon(resultComponent, element);

        loadVisible(resultComponent, element);
        loadEnable(resultComponent, element);

        loadBoolean(element, "scrollable", resultComponent::setScrollable);
        loadBoolean(element, "flexible", resultComponent::setFlexible);
        loadBoolean(element, "spacing", resultComponent::setSpacing);

        String containerType = element.attributeValue("containerType");
        if (isNotEmpty(containerType)) {
            resultComponent.setContainerType(ContainerType.valueOf(containerType));
        }

        // load rows first
        for (PendingRow pendingRow : pendingRows) {
            ResponsiveLayout.Row row = pendingRow.getRow();
            Element rowElement = pendingRow.getElement();

            loadRow(row, rowElement);
        }

        // load columns
        for (PendingColumn pendingColumn : pendingColumns) {
            ResponsiveLayout.Column column = pendingColumn.getColumn();
            Element columnElement = pendingColumn.getElement();

            loadColumn(column, columnElement);
        }

        // load child components of columns
        loadSubComponents();
    }

    protected void loadRow(ResponsiveLayout.Row row, Element rowElement) {
        assignFrame(row);

        loadBasicAttributes(row, rowElement);

        String defaultComponentAlignment = rowElement.attributeValue("defaultComponentAlignment");
        if (isNotEmpty(defaultComponentAlignment)) {
            row.setDefaultComponentAlignment(Alignment.valueOf(defaultComponentAlignment));
        }

        loadBoolean(rowElement, "spacing", row::setSpacing);
        loadBoolean(rowElement, "verticalSpacing", row::setVerticalSpacing);
        loadBoolean(rowElement, "horizontalSpacing", row::setHorizontalSpacing);
        loadBoolean(rowElement, "margin", row::setMargin);

        loadBoolean(rowElement, "grow", row::setGrow);
        loadBoolean(rowElement, "shrink", row::setShrink);

        loadString(rowElement, "minWidth", row::setMinWidth);
        loadString(rowElement, "maxWidth", row::setMaxWidth);

        loadString(rowElement, "minHeight", row::setMinHeight);
        loadString(rowElement, "maxHeight", row::setMaxHeight);

        loadMarginSize(row, rowElement, DisplaySize.XS, "marginSizeXS");
        loadMarginSize(row, rowElement, DisplaySize.SM, "marginSizeSM");
        loadMarginSize(row, rowElement, DisplaySize.MD, "marginSizeMD");
        loadMarginSize(row, rowElement, DisplaySize.LG, "marginSizeLG");

        String spacingSize = rowElement.attributeValue("spacingSize");
        if (isNotEmpty(spacingSize)) {
            row.setSpacing(SpacingSize.valueOf(spacingSize), true);
        }

        String verticalSpacingSize = rowElement.attributeValue("verticalSpacingSize");
        if (isNotEmpty(verticalSpacingSize)) {
            row.setVerticalSpacing(SpacingSize.valueOf(verticalSpacingSize), true);
        }

        String horizontalSpacingSize = rowElement.attributeValue("horizontalSpacingSize");
        if (isNotEmpty(horizontalSpacingSize)) {
            row.setHorizontalSpacing(SpacingSize.valueOf(horizontalSpacingSize), true);
        }
    }

    protected void loadMarginSize(ResponsiveLayout.Row row, Element rowElement,
                                  DisplaySize displaySize, String marginAttribute) {
        String marginSize = rowElement.attributeValue(marginAttribute);
        if (isNotEmpty(marginSize)) {
            row.setMargin(ResponsiveLayout.MarginSize.valueOf(marginSize), displaySize);
        }
    }

    protected void loadColumn(ResponsiveLayout.Column column, Element columnElement) {
        assignFrame(column);

        loadBasicAttributes(column, columnElement);

        // content attributes
        Element contentElement = columnElement.element("content");
        if (contentElement != null) {
            String alignment = contentElement.attributeValue("alignment");
            if (isNotEmpty(alignment)) {
                column.setContentAlignment(ContentAlignment.valueOf(alignment));
            }
        }

        // display rules
        Element displayElement = columnElement.element("display");
        if (displayElement != null) {
            int xs = parseInt(displayElement.attributeValue("xs"));
            int sm = parseInt(displayElement.attributeValue("sm"));
            int md = parseInt(displayElement.attributeValue("md"));
            int lg = parseInt(displayElement.attributeValue("lg"));

            column.setDisplayRules(xs, sm, md, lg);
        }

        // visibility
        Element visibilityElement = columnElement.element("visibility");
        if (visibilityElement != null) {
            boolean xs = parseBoolean(visibilityElement.attributeValue("xs"));
            boolean sm = parseBoolean(visibilityElement.attributeValue("sm"));
            boolean md = parseBoolean(visibilityElement.attributeValue("md"));
            boolean lg = parseBoolean(visibilityElement.attributeValue("lg"));

            column.setVisibilityRules(xs, sm, md, lg);
        }

        List<Element> ruleElements = Dom4j.elements(columnElement, "rule");
        for (Element ruleElement : ruleElements) {
            DisplaySize displaySize = DisplaySize.valueOf(ruleElement.attributeValue("displaySize"));
            int width = parseInt(ruleElement.attributeValue("width"));

            column.addRule(displaySize, width);
        }

        loadOffset(column, columnElement, DisplaySize.XS, "offsetXS");
        loadOffset(column, columnElement, DisplaySize.SM, "offsetSM");
        loadOffset(column, columnElement, DisplaySize.MD, "offsetMD");
        loadOffset(column, columnElement, DisplaySize.LG, "offsetLG");

        ResponsiveLayout.Row row = (ResponsiveLayout.Row) column.getParent();
        loadExpandRatio(row, column, columnElement, DisplaySize.XS, "expandRatioXS");
        loadExpandRatio(row, column, columnElement, DisplaySize.SM, "expandRatioSM");
        loadExpandRatio(row, column, columnElement, DisplaySize.MD, "expandRatioMD");
        loadExpandRatio(row, column, columnElement, DisplaySize.LG, "expandRatioLG");
    }

    protected void loadOffset(ResponsiveLayout.Column column, Element columnElement,
                              DisplaySize displaySize, String offsetAttribute) {
        String value = columnElement.attributeValue(offsetAttribute);
        if (isNotEmpty(value)) {
            column.setOffset(displaySize, parseInt(value));
        }
    }

    protected void loadExpandRatio(ResponsiveLayout.Row row, ResponsiveLayout.Column column, Element columnElement,
                                   DisplaySize displaySize, String offsetAttribute) {
        String value = columnElement.attributeValue(offsetAttribute);
        if (isNotEmpty(value)) {
            row.setExpandRatio(column, displaySize, parseInt(value));
        }
    }

    protected void loadBasicAttributes(Component component, Element element) {
        loadAlign(component, element);

        loadWidth(component, element);
        loadHeight(component, element);
        loadStyleName(component, element);
        loadResponsive(component, element);

        loadVisible(component, element);
        loadEnable(component, element);
    }

    public static class PendingRow {
        private ResponsiveLayout.Row row;
        private Element element;

        public PendingRow(ResponsiveLayout.Row row, Element element) {
            this.row = row;
            this.element = element;
        }

        public ResponsiveLayout.Row getRow() {
            return row;
        }

        public Element getElement() {
            return element;
        }
    }

    public static class PendingColumn {
        private ResponsiveLayout.Column column;
        private Element element;

        public PendingColumn(ResponsiveLayout.Column column, Element element) {
            this.column = column;
            this.element = element;
        }

        public ResponsiveLayout.Column getColumn() {
            return column;
        }

        public Element getElement() {
            return element;
        }
    }

    public static void loadBoolean(Element element, String attribute, Consumer<Boolean> consumer) {
        String value = element.attributeValue(attribute);
        if (isNotEmpty(value)) {
            consumer.accept(Boolean.valueOf(value));
        }
    }

    public static void loadString(Element element, String attribute, Consumer<String> consumer) {
        String value = element.attributeValue(attribute);
        if (isNotEmpty(value)) {
            consumer.accept(value);
        }
    }
}