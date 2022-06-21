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

import pt.ipbeja.po2.chartracer.model.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkinHandler {
    private final Map<String, ChartSkin> skins;
    private final List<Listener> listeners;
    private ChartSkin currentSkin;

    public SkinHandler() {
        this.skins = new HashMap<>();
        this.listeners = new ArrayList<>();
        this.registerAllSkins();
        this.currentSkin = this.getDefault();
    }

    /**
     * Get the current skin.
     *
     * @return The current skin.
     */
    public ChartSkin current() {
        return this.currentSkin;
    }

    /**
     * Set the current skin.
     *
     * @param name The name of the skin. MUST exist, or an AssertionError
     *             will be thrown.
     */
    public void changeSkin(String name) {
        ChartSkin skin = this.skins.get(name);
        assert skin != null;

        this.currentSkin = skin;

        for (int i = 0; i < this.listeners.size(); i++) {
            this.listeners.get(i).onSkinChange(skin);
        }
    }

    /**
     * Get the default skin.
     *
     * @return The default skin.
     */
    public ChartSkin getDefault() {
        return skins.get(Constants.DEFAULT_SKIN_NAME);
    }

    /**
     * Get all skins.
     *
     * @return All skins.
     */
    public List<ChartSkin> getAllSkins() {
        return this.skins.values().stream().toList();
    }

    public void addOnSkinChangeListener(Listener changeSkinListener) {
        this.listeners.add(changeSkinListener);
    }

    private void registerAllSkins() {
        this.registerSkin(new ClassicSkin(this));
        this.registerSkin(new DarkModeSkin(this));
        this.registerSkin(new MinimalSkin(this));
    }

    private void registerSkin(ChartSkin skin) {
        this.skins.put(skin.name(), skin);
    }

    public interface Listener {
        void onSkinChange(ChartSkin newSkin);
    }
}
