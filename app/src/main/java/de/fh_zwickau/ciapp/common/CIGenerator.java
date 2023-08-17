package de.fh_zwickau.ciapp.common;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import de.fh_zwickau.ciapp.models.CI;

public class CIGenerator {
    private static int id = 1;
    private final SharedPreferences sharedPreferences;

    public CIGenerator(Activity activity) {
        this.sharedPreferences = activity.getSharedPreferences(ConstantsObject.CI_ID_COUNTER, MODE_PRIVATE);
        id = sharedPreferences.getInt(ConstantsObject.CI_ID_COUNTER, 1);
    }

    public CI generateCI() {
        String title = "TestCI_" + id;
        String story = "CI_" + id + "Facilisi nullam vehicula ipsum a arcu cursus vitae congue mauris. Eu feugiat pretium nibh ipsum consequat nisl. Diam quam nulla porttitor massa id neque aliquam. Tincidunt id aliquet risus feugiat in. Enim sed faucibus turpis in. At tellus at urna condimentum mattis pellentesque id. Consectetur adipiscing elit duis tristique sollicitudin. Massa placerat duis ultricies lacus sed turpis tincidunt. Mi bibendum neque egestas congue quisque egestas diam in. Ut morbi tincidunt augue interdum velit euismod in pellentesque. Mollis nunc sed id semper risus in hendrerit gravida rutrum. Auctor augue mauris augue neque gravida. Et odio pellentesque diam volutpat commodo sed egestas. Erat pellentesque adipiscing commodo elit. Sapien et ligula ullamcorper malesuada. Pellentesque habitant morbi tristique senectus et netus et.";
        String place = "Zwickau";
        String perspective = "Test";
        Boolean isFavorite = false;
        String author = "CIGenerator.java";
        id++;
        return new CI(id, title, story, place, perspective, isFavorite, author);
    }

    public List<CI> generateTenCIs() {
        ArrayList<CI> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            result.add(generateCI());
        }
        return result;
    }

    public List<CI> getDefaultData() {
        List<CI> cIList = new ArrayList<>();
        cIList.add(new CI(9933, "The deep Finish Forest", "This happened 23 years ago. We (my husband, my two daughters 3 and 6 years old and me) were invited to a marriage in Finland near Tampere. Our friends told us that they rented a hut for us next to Tampere. We were very happy. We arrived in Helsinki by plane and rented a car to go to Tampere. \nThere, our friends told us to follow them by car. They would bring us to our hut. (Mökki) \nWe went to the main road and drove for about 50 kilometers. I started to be a bit angry because it was so far. Then we suddenly turned to the right and took a path into the forest. We imagined to arrive in a few minutes. But then our friends went on and on. They turned left and right and left…. \nSuddenly I was afraid because it was so far and I couldn´t remember the way anymore. I told to my husband: “We will never again find the way back!”. After 30 minutes we finally arrived at our hut. \nI told my friends: \"But what did you tell us? This is not next to Tampere. And how shall we ever find the way back?\"\nFortunately we had one of the first mobile-phones from Motorola with us. \nWithout that and with two small children I would have been really very afraid in this situation. \n", "Finland", "First person", true, "Example"));
        cIList.add(new CI(7134, "Busfahren", "Während meines Urlaubs in Rimini wollte ich mit zwei Freunden einen Ausflug unternehmen. Wir entschieden uns mit dem Bus zu fahren, da es zu Fuß ein zu langer Weg gewesen wäre. Genau wie in Deutschland gibt es auch in Italien Haltestellenschilder, die darauf hinweisen, wo der Bus hält. Nach ein paar Minuten warten, kam er dann auch gleich. Doch obwohl wir direkt an der Haltestelle standen und der Busfahrer uns auch sah fuhr er weiter… Später haben wir erfahren, dass man den Fahrer immer mit einem Winkzeichen darauf aufmerksam machen muss, wenn man mitfahren will! Tja, das muss man erstmal wissen!!!\n", "Italy", "First person", false, "Example"));
        cIList.add(new CI(6091, "Wie putzt mann denn eigentlich?", "M. ist Geschäftsführer einer französischen Bäckerei in Deutschland. Eine Verkäuferin S. ist marokkanischer Herkunft. M. erzählt, dass er sehr wütend auf S. ist. Sie sei gefährlich für die Bäckerei. Ich frage nach dem Grund. S. wollte im Laden putzen und hat einen vollen Wassereimer auf den Boden geschüttet. Das Wasser lief durch ein Rohr am Boden in die elektrischen Leitungen, so dass ein Kurzschluss entstand. Alle Geräte fielen aus und der Sicherungskasten brannte halb ab. S., die zu diesem Zeitpunkt alleine im Laden war, hatte große Angst und informierte M. sofort telefonisch. M. fragte am Telefon, ob sie denn Wasser verschüttet hätte, was S. verneinte. Später erklärte Sie, dass Sie nur sorgfältig putzen wollte. Ich persönlich weiß, dass man in Algerien oft so putzt, dass man mit viel Schwung das Wasser aus dem Putzeimer über den Boden gießt. Hat S. das auch so gemacht? \nM. sagt jetzt, dass S. ihn angelogen hat und will sie deshalb entlassen.", "Deutschland", "Third person", false, "Example"));
        cIList.add(new CI(7106, "Am ersten Tag in Deutschland", "Es ist am ersten Tag als ich in Deutschland ankam passiert: und es war die Begrüßung. Vorher muss ich sagen, dass meine Gastfamilie mich im Flughafen abgeholt hat. Das Kennenlernen wurde von meiner Seite mit georgischer Gewohnheit begleitet: Umarmen und Küssen. Aber die Deutschen standen leider wie sturr. \"Etwas stimmt nicht\" -dachte ich- \"Entweder darf man nicht beim Kennenlernen küssen oder sie sind gar nicht freundlich\". (Sie sind sehr freundlich- wie es sich ergab). \nLeider ist es bis heute so: wenn ich meine Gasteltern treffe, weiß ich nicht, ob ich sie bei der Begrüßung auch umarmen darf, und wenn ich umarme, dabei küssen? Gar nicht leicht, aber fragen möchte ich irgendwie nicht. \n", "Germany", "First person", true, "Example"));
        cIList.add(new CI(7982, "Los españoles huelen a ajo", "Unos tres meses después de llegar a Essen, Alemania, procedente de Madrid, España, tenía que ir a la oficina de extranjeros para registrarme como residente en Alemania. Mi dominio del alemán no era muy bueno, pero suficiente (probablemente entre B1 y B2), pero nunca había tenido que hacer algo similar antes y menos en una lengua extranjera. En la oficina había varias personas que recibían a los visitantes detras de mesas de trabajo. Había que sacar un número y esperar su turno. Tras unos 20 minutos de espera, que me hicieron ponerme aún más nervioso. Me tocó el turno. La señora que me recibió me preguntó qué deseaba y le contestó \"soy español y...\". Iba a decir \"soy español y quiere registrarme como residente\", pero la empleada me interrumpió y, con sonrisa beatífica, me dijó: \"usted es español y huele a ajo\". Me sentí ofendido, pero decidí callarme porque pensé que yo era quien quería solicitar algo y ella la que decidía si me lo daba. Pensé que si hubiera ocurrido en una lengua que yo dominará o en un entorno conocido para mí, habría solicitado hablar con su superior. Siempre me ha quedado la duda de por qué dijo eso. Tal vez no había querido humillarme. Tal vez era el entusiasmo por un \"auténtico\" español que olía como se supone que tienen que oler los españoles lo que la movía. Huelga añadir que yo no podía oler a ajo, hacía días que no había comido ajo en ninguna forma y acostumbro a duchar me a diario.", "Germany", "First person", true, "Example"));
        cIList.add(new CI(7040, "Verkaufsstrategie in Polen", "In Polen hat sich ein großes deutsches Unternehmen niedergelassen und mehrere Verkaufsfilialen eingerichtet. Die Mitarbeiter und die Verkäufer sind Polen, der Geschäftsführer ist ein deutscher Manager. Bei einer großen Besprechung mit den Verkäufern instruiert der Deutsche die polnischen Verkäufer über die Verkaufsstrategie und sagt: „Sie sollten als Verkäufer bitte nicht aufdringlich sein“. Nach einigen Monaten stellt sich heraus, dass die Umsatzzahlen stark heruntergegangen sind. \n", "Poland", "Third person", false, "Example"));
        return cIList;
    }

    public void saveCounterValue() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(ConstantsObject.CI_ID_COUNTER, id);
        editor.apply();
    }
}
