package nu.spiritusmundi.inteokej.android;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

public class FakeDatabase {
	
	private static ArrayList<Question> questions;
	
	private static String currentUserName = "";
	
	private static HashMap map;
	
	public FakeDatabase() {
	}
	
	/**
	 * Creates the database and fills it with garbage
	 */
	public static void create()
	{
		questions = new ArrayList<Question>();
		
		//Fyll fake-databasen med meningsfullt innehåll enligt den här mallen:
		Question q = new Question("Jag blir mobbad på WoW :(", "Det är verkligen jobbigt och jag mår himla dåligt, ", "kalle_boy_98", Tag.SPEL);
		q.support("jojje");
		q.support("jopckiboi");
		q.support("godzilla");
		q.support("jens-olle");
		q.support("bla bla");
		
		q.getAnswers().add(new Answer("stackars dig!", "crazy_girl_95"));
		q.getAnswers().add(new Answer("det ordnar sig ska du se!", "TonySoprano"));
		Answer a = new Answer("kämpa på!", "kalle");
		a.like("jugge");
		a.like("dogge");
		q.getAnswers().add(a);
		
		questions.add(q);
		
		Question q2 = new Question("Jag har inga venner på lunar :( ", "", "nogger_black", Tag.SEX);
		q2.support("hahahha");
		
		q2.getAnswers().add(new Answer("", ""));
		questions.add(q2);
		questions.add(new Question("Jag blir uthängd på min klasskompis blogg ", "En klasskompis skriver saker om mig på sin blogg om mig och min pojkvän… Hon låtsas som ingenting i klassrummmet men på sin blogg är hon jättetaski. Skriver saker som hon har hört att vi har gjort i sänger som inte alls är sant.. Vad ska jag göra?? Vet inte varför hon gör så ju!:(", "Lilla_my", Tag.SEX));
		questions.add(new Question("Inga vännner?", "Jag har typ inga vänner på facebook… har skickat ut till alla i klassen men ingen vill bli vän… känns som om dom gaddar ihop sig fö dom slutar alltid att prata när jag går förbi!! Jag fattar inte vafö… Ja har ju inte gjort någon nått", "emonemo", Tag.FACEBOOK));
		questions.add(new Question("Dom trakasserar mig på JA", "Ja spelar jedi academy med mina kompisar osså brukar vi köra lite rp på de.. då är ja o en kompis padwans och en är jedi master… men när vi ska gå runt o kaxa me folk så dödar den andra padwan mig allti.. även om mastern säger att han inte ska göra så.. men han fattar ju inte vad rp är… och jag kan ju inte döda honom för att min master säger nej.. men hans score blir mycket bättre och de kaxar han me så ja… va ska ja göra? Assåå finns väl ingen lösning men lixom hur kan jag kanske göra? Skitjobbigt att ha så low score alltid!!", "tarzanboy", Tag.SPEL));
		questions.add(new Question("Äckliga mail", "Sedan två veckor tillbaka får jag jättemånga äckliga mail från min styvfar där han skriver konskiga sakker… typ fö nån vecka sen skrev han att han hoppades att jag gillade klänningen som han gav mig till födelsedan… Och sen skrev han att han tyckte att jag kunde fota när jag hade på mig den och skicka till han. Jag bor med min riktiga pappa och träffar ba min plastpappa när jag har helg hos min mamma… och det är bara typ en vecka kvar nu till de.. Jag vet inte vad ja ska göra! Ska jag säga till min mamma eller inte? Han har ju inte gjort något fel utan bara skrivit lite mail till mig… ", "lillaknytemo", Tag.TRAKASSERING));
		questions.add(new Question("Konstiga vänförfrågningar", "Jag trorja har hamnat i nått register för pedofiler… för jag får massa konstiga vänförfrågningar på facebook… och när jag inte svarar eller tacka nei så börjar de puffa mej… osså skickar de meddelanden och frågar om jag gillar kläder och att de kanske kan ge mig lite fickpengar… Jag har anmält dom till facebook så det är inte desamma som kommer tillbaka.. men det är nya helatiden… hur kan jag stoppa dem?", "1337emogrrl", Tag.FACEBOOK));
		questions.add(new Question("Någon har kapat mitt konto", "Det verkar som om någon har kapat mitt konto här på hemsidan och skrivit massa läskiga svar till folk!! Vad ska jag göra? Jag har bytt lösenord så jag hoppas att det inte händer igen!!", "Gr00mingGuy45", Tag.BLOGG));
		
		questions.add(new Question("min blogg har inga läsare!", "", "", Tag.BLOGG));
		questions.add(new Question("de bloggar elakt om mig!", "", "", Tag.BLOGG));
		questions.add(new Question("min mamma dricker", "wa wa wa ", "krille", Tag.ALKOHOL));
		
		questions.get(2).getAnswers().add(new Answer("Hände mig med!! Jag ställde henne mot väggen och frågade henne vafö hon höll på sådär, då slutade hon.", "Icequeen"));
		questions.get(2).getAnswers().add(new Answer("Strunta i henne! Hon är inte värd din uppmärksamhet!", "arnebarne"));
		questions.get(2).getAnswers().add(new Answer("Häng ut henne på din egen blogg vetja! Så får hon känna hur det känns!", "lutten"));
		
		questions.get(3).getAnswers().add(new Answer("Facebook är skit!!!", "LalleRalleball"));
		questions.get(3).getAnswers().add(new Answer("Skit i dom! Häng med dina riktiga vänner istället!", "HateahEmo"));
		/*
		 * Below is liking the second answer to the fourth question.
		 * */
		questions.get(3).getAnswers().get(1).like("TeoFranzen");
		
		
		questions.get(4).getAnswers().add(new Answer("JA är skit!!", "LalleRalleball"));
		questions.get(4).getAnswers().add(new Answer("Han den andra padwan verkar du vara dum i huet… Snacka med mastern o säj att han som master måste sätta gränser!", "Punkhars"));
		
		questions.get(5).getAnswers().add(new Answer("Svara på hans mejl o skriv att han ska sluta. Att du inte vill ha mejlkontakt med honom! Äckelgubbe!", "colakungen"));
		questions.get(5).getAnswers().add(new Answer("Säg till din mamma! Du förstör inget, hon vill inte vara ihop med honom om han beter sig så! ", "orvarskorvar"));
		questions.get(5).getAnswers().add(new Answer("Usch vad äckligt! Hoppas det löser sig!", "Horde_pwnz"));
		questions.get(5).getAnswers().add(new Answer("Säg till din mamma innan han gör annat… Du har mitt stöd!", "jonazzz"));
		questions.get(5).getAnswers().add(new Answer("Hände mig med… Ja sa till och allt löste sig!", "leoparden"));
		
		
		questions.get(7).getAnswers().add(new Answer("Hatar när det händer! Hoppas det löser sig", "Vampirechick"));
		
	}
	
	/**
	 * 
	 * Returns a list containing all the questions stored in the database
	 */
	public static ArrayList<Question> getLatestQuestions()
	{
		ArrayList<Question> latestQuestions = questions;
		
		Collections.sort(latestQuestions, new Comparator<Question>() {

			@Override
			public int compare(Question q1, Question q2) {
	            return (int) (q1.getDate().getTime() - q2.getDate().getTime());
			}
		});
		
		Collections.reverse(latestQuestions);
		
		return latestQuestions;
	}
	
	public static ArrayList<Question> getUnansweredQuestions()
	{
		ArrayList<Question> unansweredQuestions = new ArrayList<Question>();
		for(int i = 0; i < questions.size(); i ++){
			if(questions.get(i).getAnswers().size() == 0){
				unansweredQuestions.add(questions.get(i));
			}
		}
		return unansweredQuestions;
	}
	
	/**
	 * Adds a new question to the database
	 * 
	 * @param question the question
	 */
	public static void postNewQuestion(Question question)
	{
		questions.add(question);
	}
	
	/**
	 * Adds a new answer to a specified question
	 * @param question
	 * @param answer
	 */
	public static void answerQuestion(Question question, Answer answer)
	{
		question.getAnswers().add(answer);
	}
	
	/**
	 * Adds support to a specified question
	 * @param question
	 */
	public static boolean supportQuestion(Question question)
	{
		if(question.getUsersWhoSupportsThis().indexOf(currentUserName) == -1){
			question.support(currentUserName);
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean flagQuestion(Question question)
	{
		if(question.getUsersWhoFlaggedThis().indexOf(currentUserName) == -1){
			question.flag(currentUserName);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param answer
	 */
	public static boolean likeAnswer(Answer answer)
	{
		if(answer.getUsersWhoLikeThis().indexOf(currentUserName) == -1){
			answer.like(currentUserName);
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean flagAnswer(Answer answer)
	{
		if(answer.getUsersWhoFlaggedThis().indexOf(currentUserName) == -1){
			answer.flag(currentUserName);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns all questions tagged with a specified tag
	 * @param tag the specified tag
	 * @return
	 */
	public static ArrayList<Question> getFilteredQuestions(String tag)
	{
		ArrayList<Question> filteredQuestions = new ArrayList<Question>();
		for(int i = 0; i < questions.size(); i ++){
			if(questions.get(i).getTag().equals(tag)){
				filteredQuestions.add(questions.get(i));
			}
		}
		return filteredQuestions;
	}
	/**
	 * Returns all the questions sorted by the number of supports
	 * @return
	 */
	public static ArrayList<Question> getHottestQuestions()
	{
		ArrayList<Question> hottestQuestions = questions;
		
		Collections.sort(hottestQuestions, new Comparator<Question>() {

			@Override
			public int compare(Question q1, Question q2) {
	            return q1.getSupporters().size() - q2.getSupporters().size();
			}
		});
		
		Collections.reverse(hottestQuestions);
		
		return hottestQuestions;
	}
	
	/**
	 * 
	 * @param userName
	 */
	public static void login(String userName)
	{
		currentUserName = userName;
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getCurrentUserName()
	{
		return currentUserName;
	}
	
	public static void viewQuestion(Question question)
	{
		question.view();
	}
	
	public static ArrayList<String> getSortedTags()
	{
		//ArrayList<String> sortedTags = new ArrayList<String>();
		ArrayList<String> occuringTags = new ArrayList<String>();
		
		map = new HashMap();
		
		 
		
		
		for(int i = 0; i < questions.size(); i ++){
			if(occuringTags.indexOf(questions.get(i).getTag()) == -1)
			{
				occuringTags.add(questions.get(i).getTag());
			}
			
			map.put(questions.get(i).getTag(), questions.get(i));
		}
		
		Set set = map.keySet();
		
		
		
		
		
		return occuringTags;
	}
	
	
}
