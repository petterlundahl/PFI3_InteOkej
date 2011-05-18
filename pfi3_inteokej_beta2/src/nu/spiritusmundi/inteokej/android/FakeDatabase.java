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
		
		//Fyll fake-databasen med meningsfullt inneh�ll enligt den h�r mallen:
		Question q = new Question("Jag blir mobbad p� WoW :(", "Det �r verkligen jobbigt och jag m�r himla d�ligt, ", "kalle_boy_98", Tag.SPEL);
		q.support("jojje");
		q.support("jopckiboi");
		q.support("godzilla");
		q.support("jens-olle");
		q.support("bla bla");
		
		q.getAnswers().add(new Answer("stackars dig!", "crazy_girl_95"));
		q.getAnswers().add(new Answer("det ordnar sig ska du se!", "TonySoprano"));
		Answer a = new Answer("k�mpa p�!", "kalle");
		a.like("jugge");
		a.like("dogge");
		q.getAnswers().add(a);
		
		questions.add(q);
		
		Question q2 = new Question("Jag har inga venner p� lunar :( ", "", "nogger_black", Tag.SEX);
		q2.support("hahahha");
		
		q2.getAnswers().add(new Answer("", ""));
		questions.add(q2);
		questions.add(new Question("Jag blir uth�ngd p� min klasskompis blogg ", "En klasskompis skriver saker om mig p� sin blogg om mig och min pojkv�n� Hon l�tsas som ingenting i klassrummmet men p� sin blogg �r hon j�ttetaski. Skriver saker som hon har h�rt att vi har gjort i s�nger som inte alls �r sant.. Vad ska jag g�ra?? Vet inte varf�r hon g�r s� ju!:(", "Lilla_my", Tag.SEX));
		questions.add(new Question("Inga v�nnner?", "Jag har typ inga v�nner p� facebook� har skickat ut till alla i klassen men ingen vill bli v�n� k�nns som om dom gaddar ihop sig f� dom slutar alltid att prata n�r jag g�r f�rbi!! Jag fattar inte vaf�� Ja har ju inte gjort n�gon n�tt", "emonemo", Tag.FACEBOOK));
		questions.add(new Question("Dom trakasserar mig p� JA", "Ja spelar jedi academy med mina kompisar oss� brukar vi k�ra lite rp p� de.. d� �r ja o en kompis padwans och en �r jedi master� men n�r vi ska g� runt o kaxa me folk s� d�dar den andra padwan mig allti.. �ven om mastern s�ger att han inte ska g�ra s�.. men han fattar ju inte vad rp �r� och jag kan ju inte d�da honom f�r att min master s�ger nej.. men hans score blir mycket b�ttre och de kaxar han me s� ja� va ska ja g�ra? Ass�� finns v�l ingen l�sning men lixom hur kan jag kanske g�ra? Skitjobbigt att ha s� low score alltid!!", "tarzanboy", Tag.SPEL));
		questions.add(new Question("�ckliga mail", "Sedan tv� veckor tillbaka f�r jag j�ttem�nga �ckliga mail fr�n min styvfar d�r han skriver konskiga sakker� typ f� n�n vecka sen skrev han att han hoppades att jag gillade kl�nningen som han gav mig till f�delsedan� Och sen skrev han att han tyckte att jag kunde fota n�r jag hade p� mig den och skicka till han. Jag bor med min riktiga pappa och tr�ffar ba min plastpappa n�r jag har helg hos min mamma� och det �r bara typ en vecka kvar nu till de.. Jag vet inte vad ja ska g�ra! Ska jag s�ga till min mamma eller inte? Han har ju inte gjort n�got fel utan bara skrivit lite mail till mig� ", "lillaknytemo", Tag.TRAKASSERING));
		questions.add(new Question("Konstiga v�nf�rfr�gningar", "Jag trorja har hamnat i n�tt register f�r pedofiler� f�r jag f�r massa konstiga v�nf�rfr�gningar p� facebook� och n�r jag inte svarar eller tacka nei s� b�rjar de puffa mej� oss� skickar de meddelanden och fr�gar om jag gillar kl�der och att de kanske kan ge mig lite fickpengar� Jag har anm�lt dom till facebook s� det �r inte desamma som kommer tillbaka.. men det �r nya helatiden� hur kan jag stoppa dem?", "1337emogrrl", Tag.FACEBOOK));
		questions.add(new Question("N�gon har kapat mitt konto", "Det verkar som om n�gon har kapat mitt konto h�r p� hemsidan och skrivit massa l�skiga svar till folk!! Vad ska jag g�ra? Jag har bytt l�senord s� jag hoppas att det inte h�nder igen!!", "Gr00mingGuy45", Tag.BLOGG));
		
		questions.add(new Question("min blogg har inga l�sare!", "", "", Tag.BLOGG));
		questions.add(new Question("de bloggar elakt om mig!", "", "", Tag.BLOGG));
		questions.add(new Question("min mamma dricker", "wa wa wa ", "krille", Tag.ALKOHOL));
		
		questions.get(2).getAnswers().add(new Answer("H�nde mig med!! Jag st�llde henne mot v�ggen och fr�gade henne vaf� hon h�ll p� s�d�r, d� slutade hon.", "Icequeen"));
		questions.get(2).getAnswers().add(new Answer("Strunta i henne! Hon �r inte v�rd din uppm�rksamhet!", "arnebarne"));
		questions.get(2).getAnswers().add(new Answer("H�ng ut henne p� din egen blogg vetja! S� f�r hon k�nna hur det k�nns!", "lutten"));
		
		questions.get(3).getAnswers().add(new Answer("Facebook �r skit!!!", "LalleRalleball"));
		questions.get(3).getAnswers().add(new Answer("Skit i dom! H�ng med dina riktiga v�nner ist�llet!", "HateahEmo"));
		/*
		 * Below is liking the second answer to the fourth question.
		 * */
		questions.get(3).getAnswers().get(1).like("TeoFranzen");
		
		
		questions.get(4).getAnswers().add(new Answer("JA �r skit!!", "LalleRalleball"));
		questions.get(4).getAnswers().add(new Answer("Han den andra padwan verkar du vara dum i huet� Snacka med mastern o s�j att han som master m�ste s�tta gr�nser!", "Punkhars"));
		
		questions.get(5).getAnswers().add(new Answer("Svara p� hans mejl o skriv att han ska sluta. Att du inte vill ha mejlkontakt med honom! �ckelgubbe!", "colakungen"));
		questions.get(5).getAnswers().add(new Answer("S�g till din mamma! Du f�rst�r inget, hon vill inte vara ihop med honom om han beter sig s�! ", "orvarskorvar"));
		questions.get(5).getAnswers().add(new Answer("Usch vad �ckligt! Hoppas det l�ser sig!", "Horde_pwnz"));
		questions.get(5).getAnswers().add(new Answer("S�g till din mamma innan han g�r annat� Du har mitt st�d!", "jonazzz"));
		questions.get(5).getAnswers().add(new Answer("H�nde mig med� Ja sa till och allt l�ste sig!", "leoparden"));
		
		
		questions.get(7).getAnswers().add(new Answer("Hatar n�r det h�nder! Hoppas det l�ser sig", "Vampirechick"));
		
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
