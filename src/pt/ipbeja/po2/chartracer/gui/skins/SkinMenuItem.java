/**
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @author Tierri Ferreira <22897@stu.ipbeja.pt>
 */
package pt.ipbeja.po2.chartracer.gui.skins;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import pt.ipbeja.po2.chartracer.model.util.Constants;
import pt.ipbeja.po2.chartracer.model.util.Util;

public abstract class SkinMenuItem extends CheckMenuItem implements ChartSkin {
    private final SkinHandler handler;

    public SkinMenuItem(SkinHandler handler) {
        this.handler = handler;
        this.setSelected(this.name().equals(Constants.DEFAULT_SKIN_NAME));

        this.setText(Util.capitalize(this.name()));
        this.setOnAction(this::handleClick);
    }

    private void handleClick(ActionEvent event) {
        // This means it was just deselected, which can only be
        // the currently selected menu item.
        if (!this.isSelected()) {
            this.setSelected(true);
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "This skin was already selected.",
                    ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();

            return;
        }
        this.handler.changeSkin(this.name());
        this.getParentMenu().getItems().stream()
                .filter((item) -> !item.getText().equals(this.getText()))
                .map((item) -> (CheckMenuItem) item)
                .forEach((item) -> item.setSelected(false));
    }
}
