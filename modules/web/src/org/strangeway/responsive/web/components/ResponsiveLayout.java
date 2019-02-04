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

package org.strangeway.responsive.web.components;

import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.ComponentContainer;
import com.haulmont.cuba.gui.components.OrderedContainer;

public interface ResponsiveLayout extends OrderedContainer, Component.HasCaption, Component.HasIcon,
        Component.BelongToFrame {

    String NAME = "c10m_responsiveLayout";

    void setContainerType(ContainerType containerType);
    ContainerType getContainerType();

    void setScrollable(boolean scrollable);
    boolean isScrollable();

    void setFlexible(boolean flexible);
    boolean isFlexible();

    void setSpacing(boolean spacing);
    boolean isSpacing();

    default void addRow(Row row) {
        add(row);
    }

    default void addRows(Row... rows) {
        add(rows);
    }

    enum DisplaySize {
        XS,
        SM,
        MD,
        LG
    }

    enum ContainerType {
        FIXED,
        FLUID
    }

    enum ContentAlignment {
        LEFT,
        CENTER,
        RIGHT
    }

    enum MarginSize {
        NORMAL, SMALL, NONE
    }

    enum SpacingSize {
        NORMAL, SMALL
    }

    interface Row extends OrderedContainer, HasMinMaxDimensions, Component.BelongToFrame {
        String NAME = "c10m_responsiveRow";

        default void addColumn(Column column) {
            add(column);
        }

        void setExpandRatio(Column responsiveColumn, DisplaySize displaySize, int width);

        void setVerticalSpacing(SpacingSize spacingSize, boolean verticalSpacing);
        void setHorizontalSpacing(SpacingSize spacingSize, boolean horizontalSpacing);

        void setVerticalSpacing(boolean verticalSpacing);
        void setHorizontalSpacing(boolean horizontalSpacing);

        void setDefaultComponentAlignment(Alignment defaultAlignment);

        default void setSpacing(boolean spacing) {
            setVerticalSpacing(spacing);
            setHorizontalSpacing(spacing);
        }

        default void setSpacing(SpacingSize spacingSize, boolean spacing) {
            setVerticalSpacing(spacingSize, spacing);
            setHorizontalSpacing(spacingSize, spacing);
        }

        void setMargin(MarginSize marginSize, DisplaySize displaySize);
        void setMargin(boolean margin);

        void setGrow(boolean grow);
        void setShrink(boolean shrink);
    }

    interface Column extends ComponentContainer, Component.BelongToFrame {
        String NAME = "c10m_responsiveColumn";

        void setContent(Component component);
        Component getContent();

        void addRule(Rule rule);

        default void addRule(DisplaySize displaySize, int width) {
            Rule rule = new Rule();
            rule.setDisplaySize(displaySize);
            rule.setWidth(width);

            addRule(rule);
        }

        boolean isVisibleForDisplaySize(DisplaySize displaySize);
        void setVisibility(DisplaySize displaySize, boolean isVisible);

        void setDisplayRules(int xs, int sm, int md, int lg);
        void setVisibilityRules(boolean xs, boolean sm, boolean md, boolean lg);
        void setContentAlignment(ContentAlignment componentAlignment);
        void setOffset(DisplaySize displaySize, int width);
    }

    final class Rule {
        private DisplaySize displaySize;
        private int width;
        private boolean isOffset = false;

        public Rule() {
        }

        public DisplaySize getDisplaySize() {
            return displaySize;
        }

        public void setDisplaySize(DisplaySize displaySize) {
            this.displaySize = displaySize;
        }

        public int getWidth() {
            return width;
        }

        /**
         * @param width width in columns up to 12
         */
        public void setWidth(int width) {
            this.width = width;
        }

        public boolean isOffset() {
            return isOffset;
        }

        public void setOffset(boolean offset) {
            isOffset = offset;
        }
    }
}