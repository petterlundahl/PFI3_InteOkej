package nu.spiritusmundi.inteokej.android;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

import android.widget.PopupWindow;

public class FakeDatabase {
	
	private static ArrayList<Question> questions;
	
	private static String currentUserName = "";
		
	public static int userScore = 0;
	
	public FakeDatabase() {
	}
	
	/**
	 * Creates the database and fills it with garbage
	 */
	public static void create()
	{
		
		userScore = 38;
		
		questions = new ArrayList<Question>();
		
		
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
		userScore += 20;
	}
	
	/**
	 * Adds a new answer to a specified question
	 * @param question
	 * @param answer
	 */
	public static void answerQuestion(Question question, Answer answer)
	{
		question.getAnswers().add(answer);
		userScore += 10;
	}
	
	/**
	 * Adds support to a specified question
	 * @param question
	 */
	public static boolean supportQuestion(Question question)
	{
		if(question.getUsersWhoSupportsThis().indexOf(currentUserName) == -1){
			question.support(currentUserName);
			userScore += 3;
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
			userScore += 2;
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
	
	public static ArrayList<Question> getMyQuestionsSorted() {
		ArrayList<Question> retQuestions = new ArrayList<Question>();
		
		ArrayList<Question> questionsAlreadyRead = new ArrayList<Question>();
		
		for (int i = 0; i<questions.size();i++) {
			boolean hasUnreadAnswers = false;
			if (questions.get(i).getUserName().equals(currentUserName)){
				
				for(int n = 0; n < questions.get(i).getAnswers().size(); n++){
					if(!questions.get(i).getAnswers().get(n).getReadByUser())
						hasUnreadAnswers = true;
					}
				}
			if(hasUnreadAnswers){
				retQuestions.add(questions.get(i));
			} else {
				if(questions.get(i).getUserName().equals(currentUserName)){
					questionsAlreadyRead.add(questions.get(i));
				}
			}
		}
		
		for(int i = 0; i < questionsAlreadyRead.size(); i ++){
			retQuestions.add(questionsAlreadyRead.get(i));
		}

		return retQuestions;
	}
	
	public static int getUserQuestionCount()
	{
		int numQuestions = 0;
		
		for(Question question : questions){
			if(question.getUserName().equals(currentUserName)){
				numQuestions ++;
			}
		}
		
		return numQuestions;
	}
	
	/**
	 * 
	 * @param userName
	 */
	public static void login(String userName)
	{
		currentUserName = userName;
		
		Question q1 = new Question("Jag får äckliga kommentarer på min bilddagbok", "Folk skriver att jag är tjock och ful på bilderna jag lagt upp på bilddagboken. vet inte vilka de är ens eller varför de håller på så", userName, Tag.BLOGG);
		Answer answer = new Answer("stackars dig. finns så många äckel!", "asterix");
		answer.setReadByUser();
		answerQuestion(q1, answer);
		questions.add(q1);
		
		Question q2 = new Question("blir mobbad i skolan :(", "folk säger att jag luktar illa, men det är inte sant! de har typ gjort en facebookgrupp om mig också", userName, Tag.FACEBOOK);
		Answer answer2 = new Answer("gud vad jobbigt. varför håller folk på så. de måste ha något fel i sin hjärna", "zelda");
		answerQuestion(q2, answer2);
		questions.add(q2);
		
		Question q3 = new Question("utfryst för att jag inte spelar WOW!!", "Jag har inte råd att betala varje månad och mina föräldrar vill inte betala för det. ALLA jag känner spelar, och nu har jag ingen att hänga med :(", userName, Tag.SPEL);
		Answer answer3 = new Answer("du får hitta kompisar med andra intressen. börja träna någon sport eller nåt", "voldemortz");
		Answer answer32 = new Answer("bra att du inte spelar WOW, det förstör bara ens liv!! lita på mig", "super_mario95");
		answer3.setReadByUser();
		answerQuestion(q3, answer3);
		answer32.setReadByUser();
		answerQuestion(q3, answer32);
		questions.add(q3);
		
		Question q4 = new Question("Obehagliga telefonsamtal", "det är någon som ringer mig mitt i natten och typ andas konstigt i luren", userName, Tag.UNTAGGED);
		Answer answer4 = new Answer("stackars dig. finns så många äckel!", "jedi_master");
		Answer answer41 = new Answer("prata med dina föräldrar om det!", "spammer666");
		Answer answer42 = new Answer("anmäl det! prata med din lärare eller skolkurator eller nåt", "swedish_chef");
		answerQuestion(q4, answer4);
		answerQuestion(q4, answer41);
		answerQuestion(q4, answer42);
		questions.add(q4);
		
		populateArrayList();
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
		ArrayList<String> occuringTags = new ArrayList<String>();
		
		for(int i = 0; i < questions.size(); i ++){
			if(occuringTags.indexOf(questions.get(i).getTag()) == -1)
			{
				occuringTags.add(questions.get(i).getTag());
			}
			
		}
		
		return occuringTags;
	}
	
	private static void populateArrayList()
	{
		//Fyll fake-databasen med meningsfullt innehåll enligt den här mallen:
		Question q = new Question("Jag blir mobbad på WoW :(", "Jag får aldrig gå med mina klasskompisar och rejda. demm är typ tio stycken som spelar tilsammans o ja e den enda som inte får vara me", "kalle_boy_98", Tag.SPEL);
		q.support("jojje");
		q.support("jopckiboi");
		q.support("godzilla");
		q.support("jens-olle");
		q.support("bla bla");
		
		q.getAnswers().add(new Answer("stackars dig! det hände mig också, så jag slutade spela till slut. nu kör jag mest betapet", "crazy_girl_95"));
		q.getAnswers().add(new Answer("det ordnar sig ska du se!", "TonySoprano"));
		Answer a = new Answer("kämpa på!", "kalle");
		a.like("jugge");
		a.like("dogge");
		q.getAnswers().add(a);
		
		questions.add(q);
		
		Question q2 = new Question("Filmer på youtube om mig", "Några i min klass har lagt upp en youtube-film med bilder av mig och min kompis och en massa andra tjejer som de tagit från facebook. och så står det typ malmös värsta horor och sånt", "lollo", Tag.FOTO);
		q2.support("hahahha");
		
		q2.getAnswers().add(new Answer("Nää, fan vad dåligt. hatar folk som gör sånt, bry dig inte om dem. de är bara dumma", "jojje"));
		questions.add(q2);
		questions.add(new Question("Jag blir uthängd på min klasskompis blogg ", "En klasskompis skriver saker om mig på sin blogg om mig och min pojkvän… Hon låtsas som ingenting i klassrummmet men på sin blogg är hon jättetaski. Skriver saker som hon har hört att vi har gjort i sänger som inte alls är sant.. Vad ska jag göra?? Vet inte varför hon gör så ju!:(", "Lilla_my", Tag.SEX));
		questions.add(new Question("Inga vännner?", "Jag har typ inga vänner på facebook… har skickat ut till alla i klassen men ingen vill bli vän… känns som om dom gaddar ihop sig fö dom slutar alltid att prata när jag går förbi!! Jag fattar inte vafö… Ja har ju inte gjort någon nått", "emonemo", Tag.FACEBOOK));
		questions.add(new Question("Dom trakasserar mig på JA", "Ja spelar jedi academy med mina kompisar osså brukar vi köra lite rp på de.. då är ja o en kompis padwans och en är jedi master… men när vi ska gå runt o kaxa me folk så dödar den andra padwan mig allti.. även om mastern säger att han inte ska göra så.. men han fattar ju inte vad rp är… och jag kan ju inte döda honom för att min master säger nej.. men hans score blir mycket bättre och de kaxar han me så ja… va ska ja göra? Assåå finns väl ingen lösning men lixom hur kan jag kanske göra? Skitjobbigt att ha så low score alltid!!", "tarzanboy", Tag.SPEL));
		questions.add(new Question("Äckliga mail", "Sedan två veckor tillbaka får jag jättemånga äckliga mail från min styvfar där han skriver konskiga sakker… typ fö nån vecka sen skrev han att han hoppades att jag gillade klänningen som han gav mig till födelsedan… Och sen skrev han att han tyckte att jag kunde fota när jag hade på mig den och skicka till han. Jag bor med min riktiga pappa och träffar ba min plastpappa när jag har helg hos min mamma… och det är bara typ en vecka kvar nu till de.. Jag vet inte vad ja ska göra! Ska jag säga till min mamma eller inte? Han har ju inte gjort något fel utan bara skrivit lite mail till mig… ", "lillaknytemo", Tag.TRAKASSERING));
		questions.add(new Question("Konstiga vänförfrågningar", "Jag trorja har hamnat i nått register för pedofiler… för jag får massa konstiga vänförfrågningar på facebook… och när jag inte svarar eller tacka nei så börjar de puffa mej… osså skickar de meddelanden och frågar om jag gillar kläder och att de kanske kan ge mig lite fickpengar… Jag har anmält dom till facebook så det är inte desamma som kommer tillbaka.. men det är nya helatiden… hur kan jag stoppa dem?", "1337emogrrl", Tag.FACEBOOK));
		questions.add(new Question("Någon har kapat mitt konto", "Det verkar som om någon har kapat mitt konto här på hemsidan och skrivit massa läskiga svar till folk!! Vad ska jag göra? Jag har bytt lösenord så jag hoppas att det inte händer igen!!", "Gr00mingGuy45", Tag.BLOGG));
		
		questions.add(new Question("konstiga kommentarer på bloggen", "alltså jag får typ tio kommentarer på varje blogginlägg, om att jag är äcklig och ful, och jag tror det är mina klasskompisar som skriver det", "eddieMEDUZA", Tag.BLOGG));
		questions.add(new Question("filmade mig när jag däckat på en fest", "det var några killar på en fest som filmade mig när jag däckat i en soffa. vet inte riktigt om de gjorde nåt mer, men har hört rykten om att några i min klass sett filmen", "samantha", Tag.ALKOHOL));
		
		questions.get(6).getAnswers().add(new Answer("Hände mig med!! Jag ställde henne mot väggen och frågade henne vafö hon höll på sådär, då slutade hon.", "Icequeen"));
		questions.get(6).getAnswers().add(new Answer("Strunta i henne! Hon är inte värd din uppmärksamhet!", "arnebarne"));
		questions.get(6).getAnswers().add(new Answer("Häng ut henne på din egen blogg vetja! Så får hon känna hur det känns!", "lutten"));
		
		questions.get(7).getAnswers().add(new Answer("Facebook är skit!!!", "LalleRalleball"));
		questions.get(7).getAnswers().add(new Answer("Skit i dom! Häng med dina riktiga vänner istället!", "HateahEmo"));
		/*
		 * Below is liking the second answer to the fourth question.
		 * */
		questions.get(7).getAnswers().get(1).like("TeoFranzen");
		
		
		questions.get(7).getAnswers().add(new Answer("JA är skit!!", "LalleRalleball"));
		questions.get(7).getAnswers().add(new Answer("Han den andra padwan verkar du vara dum i huet… Snacka med mastern o säj att han som master måste sätta gränser!", "Punkhars"));
		
		questions.get(9).getAnswers().add(new Answer("Svara på hans mejl o skriv att han ska sluta. Att du inte vill ha mejlkontakt med honom! Äckelgubbe!", "colakungen"));
		questions.get(9).getAnswers().add(new Answer("Säg till din mamma! Du förstör inget, hon vill inte vara ihop med honom om han beter sig så! ", "orvarskorvar"));
		questions.get(9).getAnswers().add(new Answer("Usch vad äckligt! Hoppas det löser sig!", "Horde_pwnz"));
		questions.get(9).getAnswers().add(new Answer("Säg till din mamma innan han gör annat… Du har mitt stöd!", "jonazzz"));
		questions.get(9).getAnswers().add(new Answer("Hände mig med… Ja sa till och allt löste sig!", "leoparden"));
		
		
		questions.get(11).getAnswers().add(new Answer("Hatar när det händer! Hoppas det löser sig", "Vampirechick"));
		
	}
}
