/**
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @author Tierri Ferreira <22897@stu.ipbeja.pt>
 */
package pt.ipbeja.po2.chartracer.model.skins;

import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import pt.ipbeja.po2.chartracer.model.util.Util;

public abstract class SkinMenuItem extends MenuItem implements ChartSkin {
    private SkinHandler handler;

    public SkinMenuItem(SkinHandler handler) {
        this.handler = handler;
        this.setText(Util.capitalize(this.name()));
        this.setOnAction(this::handleClick);
    }

    private void handleClick(ActionEvent event) {
        this.handler.changeSkin(this.name());
    }
}
