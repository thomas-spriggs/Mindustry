package io.anuke.mindustry.ui.dialogs;

import io.anuke.arc.func.*;
import io.anuke.arc.graphics.*;
import io.anuke.arc.scene.ui.*;
import io.anuke.mindustry.gen.*;
import io.anuke.mindustry.graphics.*;

public class ColorPicker extends FloatingDialog{
    private Cons<Color> cons = c -> {};
    private Color current = new Color();

    public ColorPicker(){
        super("$pickcolor");
        shown(this::setup);
    }

    public void show(Color color, Cons<Color> consumer){
        this.current.set(color);
        this.cons = consumer;
        show();
    }

    void setup(){
        cont.clear();
        cont.pane(t -> {
            t.table(Tex.pane, i -> {
                i.stack(new Image(Tex.alphaBg), new Image(){{
                    setColor(current);
                    update(() -> setColor(current));
                }}).size(200f);
            }).colspan(2).padBottom(5);

            float w = 150f;

            t.row();

            t.defaults().padBottom(4);
            t.add("R").color(Pal.remove);
            t.addSlider(0f, 1f, 0.01f, current.r, current::r).width(w);
            t.row();
            t.add("G").color(Color.lime);
            t.addSlider(0f, 1f, 0.01f, current.g, current::g).width(w);
            t.row();
            t.add("B").color(Color.royal);
            t.addSlider(0f, 1f, 0.01f, current.b, current::b).width(w);
            t.row();
            t.add("A");
            t.addSlider(0f, 1f, 0.01f, current.a, current::a).width(w);
            t.row();
        });



        buttons.clear();
        addCloseButton();
        buttons.addImageTextButton("$ok", Icon.checkSmall, () -> {
            cons.get(current);
            hide();
        });
    }
}
