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
		
		Question q1 = new Question("Jag f�r �ckliga kommentarer p� min bilddagbok", "Folk skriver att jag �r tjock och ful p� bilderna jag lagt upp p� bilddagboken. vet inte vilka de �r ens eller varf�r de h�ller p� s�", userName, Tag.BLOGG);
		Answer answer = new Answer("stackars dig. finns s� m�nga �ckel!", "asterix");
		answer.setReadByUser();
		answerQuestion(q1, answer);
		questions.add(q1);
		
		Question q2 = new Question("blir mobbad i skolan :(", "folk s�ger att jag luktar illa, men det �r inte sant! de har typ gjort en facebookgrupp om mig ocks�", userName, Tag.FACEBOOK);
		Answer answer2 = new Answer("gud vad jobbigt. varf�r h�ller folk p� s�. de m�ste ha n�got fel i sin hj�rna", "zelda");
		answerQuestion(q2, answer2);
		questions.add(q2);
		
		Question q3 = new Question("utfryst f�r att jag inte spelar WOW!!", "Jag har inte r�d att betala varje m�nad och mina f�r�ldrar vill inte betala f�r det. ALLA jag k�nner spelar, och nu har jag ingen att h�nga med :(", userName, Tag.SPEL);
		Answer answer3 = new Answer("du f�r hitta kompisar med andra intressen. b�rja tr�na n�gon sport eller n�t", "voldemortz");
		Answer answer32 = new Answer("bra att du inte spelar WOW, det f�rst�r bara ens liv!! lita p� mig", "super_mario95");
		answer3.setReadByUser();
		answerQuestion(q3, answer3);
		answer32.setReadByUser();
		answerQuestion(q3, answer32);
		questions.add(q3);
		
		Question q4 = new Question("Obehagliga telefonsamtal", "det �r n�gon som ringer mig mitt i natten och typ andas konstigt i luren", userName, Tag.UNTAGGED);
		Answer answer4 = new Answer("stackars dig. finns s� m�nga �ckel!", "jedi_master");
		Answer answer41 = new Answer("prata med dina f�r�ldrar om det!", "spammer666");
		Answer answer42 = new Answer("anm�l det! prata med din l�rare eller skolkurator eller n�t", "swedish_chef");
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
		//Fyll fake-databasen med meningsfullt inneh�ll enligt den h�r mallen:
		Question q = new Question("Jag blir mobbad p� WoW :(", "Jag f�r aldrig g� med mina klasskompisar och rejda. demm �r typ tio stycken som spelar tilsammans o ja e den enda som inte f�r vara me", "kalle_boy_98", Tag.SPEL);
		q.support("jojje");
		q.support("jopckiboi");
		q.support("godzilla");
		q.support("jens-olle");
		q.support("bla bla");
		
		q.getAnswers().add(new Answer("stackars dig! det h�nde mig ocks�, s� jag slutade spela till slut. nu k�r jag mest betapet", "crazy_girl_95"));
		q.getAnswers().add(new Answer("det ordnar sig ska du se!", "TonySoprano"));
		Answer a = new Answer("k�mpa p�!", "kalle");
		a.like("jugge");
		a.like("dogge");
		q.getAnswers().add(a);
		
		questions.add(q);
		
		Question q2 = new Question("Filmer p� youtube om mig", "N�gra i min klass har lagt upp en youtube-film med bilder av mig och min kompis och en massa andra tjejer som de tagit fr�n facebook. och s� st�r det typ malm�s v�rsta horor och s�nt", "lollo", Tag.FOTO);
		q2.support("hahahha");
		
		q2.getAnswers().add(new Answer("N��, fan vad d�ligt. hatar folk som g�r s�nt, bry dig inte om dem. de �r bara dumma", "jojje"));
		questions.add(q2);
		questions.add(new Question("Jag blir uth�ngd p� min klasskompis blogg ", "En klasskompis skriver saker om mig p� sin blogg om mig och min pojkv�n� Hon l�tsas som ingenting i klassrummmet men p� sin blogg �r hon j�ttetaski. Skriver saker som hon har h�rt att vi har gjort i s�nger som inte alls �r sant.. Vad ska jag g�ra?? Vet inte varf�r hon g�r s� ju!:(", "Lilla_my", Tag.SEX));
		questions.add(new Question("Inga v�nnner?", "Jag har typ inga v�nner p� facebook� har skickat ut till alla i klassen men ingen vill bli v�n� k�nns som om dom gaddar ihop sig f� dom slutar alltid att prata n�r jag g�r f�rbi!! Jag fattar inte vaf�� Ja har ju inte gjort n�gon n�tt", "emonemo", Tag.FACEBOOK));
		questions.add(new Question("Dom trakasserar mig p� JA", "Ja spelar jedi academy med mina kompisar oss� brukar vi k�ra lite rp p� de.. d� �r ja o en kompis padwans och en �r jedi master� men n�r vi ska g� runt o kaxa me folk s� d�dar den andra padwan mig allti.. �ven om mastern s�ger att han inte ska g�ra s�.. men han fattar ju inte vad rp �r� och jag kan ju inte d�da honom f�r att min master s�ger nej.. men hans score blir mycket b�ttre och de kaxar han me s� ja� va ska ja g�ra? Ass�� finns v�l ingen l�sning men lixom hur kan jag kanske g�ra? Skitjobbigt att ha s� low score alltid!!", "tarzanboy", Tag.SPEL));
		questions.add(new Question("�ckliga mail", "Sedan tv� veckor tillbaka f�r jag j�ttem�nga �ckliga mail fr�n min styvfar d�r han skriver konskiga sakker� typ f� n�n vecka sen skrev han att han hoppades att jag gillade kl�nningen som han gav mig till f�delsedan� Och sen skrev han att han tyckte att jag kunde fota n�r jag hade p� mig den och skicka till han. Jag bor med min riktiga pappa och tr�ffar ba min plastpappa n�r jag har helg hos min mamma� och det �r bara typ en vecka kvar nu till de.. Jag vet inte vad ja ska g�ra! Ska jag s�ga till min mamma eller inte? Han har ju inte gjort n�got fel utan bara skrivit lite mail till mig� ", "lillaknytemo", Tag.TRAKASSERING));
		questions.add(new Question("Konstiga v�nf�rfr�gningar", "Jag trorja har hamnat i n�tt register f�r pedofiler� f�r jag f�r massa konstiga v�nf�rfr�gningar p� facebook� och n�r jag inte svarar eller tacka nei s� b�rjar de puffa mej� oss� skickar de meddelanden och fr�gar om jag gillar kl�der och att de kanske kan ge mig lite fickpengar� Jag har anm�lt dom till facebook s� det �r inte desamma som kommer tillbaka.. men det �r nya helatiden� hur kan jag stoppa dem?", "1337emogrrl", Tag.FACEBOOK));
		questions.add(new Question("N�gon har kapat mitt konto", "Det verkar som om n�gon har kapat mitt konto h�r p� hemsidan och skrivit massa l�skiga svar till folk!! Vad ska jag g�ra? Jag har bytt l�senord s� jag hoppas att det inte h�nder igen!!", "Gr00mingGuy45", Tag.BLOGG));
		
		questions.add(new Question("konstiga kommentarer p� bloggen", "allts� jag f�r typ tio kommentarer p� varje blogginl�gg, om att jag �r �cklig och ful, och jag tror det �r mina klasskompisar som skriver det", "eddieMEDUZA", Tag.BLOGG));
		questions.add(new Question("filmade mig n�r jag d�ckat p� en fest", "det var n�gra killar p� en fest som filmade mig n�r jag d�ckat i en soffa. vet inte riktigt om de gjorde n�t mer, men har h�rt rykten om att n�gra i min klass sett filmen", "samantha", Tag.ALKOHOL));
		
		questions.get(6).getAnswers().add(new Answer("H�nde mig med!! Jag st�llde henne mot v�ggen och fr�gade henne vaf� hon h�ll p� s�d�r, d� slutade hon.", "Icequeen"));
		questions.get(6).getAnswers().add(new Answer("Strunta i henne! Hon �r inte v�rd din uppm�rksamhet!", "arnebarne"));
		questions.get(6).getAnswers().add(new Answer("H�ng ut henne p� din egen blogg vetja! S� f�r hon k�nna hur det k�nns!", "lutten"));
		
		questions.get(7).getAnswers().add(new Answer("Facebook �r skit!!!", "LalleRalleball"));
		questions.get(7).getAnswers().add(new Answer("Skit i dom! H�ng med dina riktiga v�nner ist�llet!", "HateahEmo"));
		/*
		 * Below is liking the second answer to the fourth question.
		 * */
		questions.get(7).getAnswers().get(1).like("TeoFranzen");
		
		
		questions.get(7).getAnswers().add(new Answer("JA �r skit!!", "LalleRalleball"));
		questions.get(7).getAnswers().add(new Answer("Han den andra padwan verkar du vara dum i huet� Snacka med mastern o s�j att han som master m�ste s�tta gr�nser!", "Punkhars"));
		
		questions.get(9).getAnswers().add(new Answer("Svara p� hans mejl o skriv att han ska sluta. Att du inte vill ha mejlkontakt med honom! �ckelgubbe!", "colakungen"));
		questions.get(9).getAnswers().add(new Answer("S�g till din mamma! Du f�rst�r inget, hon vill inte vara ihop med honom om han beter sig s�! ", "orvarskorvar"));
		questions.get(9).getAnswers().add(new Answer("Usch vad �ckligt! Hoppas det l�ser sig!", "Horde_pwnz"));
		questions.get(9).getAnswers().add(new Answer("S�g till din mamma innan han g�r annat� Du har mitt st�d!", "jonazzz"));
		questions.get(9).getAnswers().add(new Answer("H�nde mig med� Ja sa till och allt l�ste sig!", "leoparden"));
		
		
		questions.get(11).getAnswers().add(new Answer("Hatar n�r det h�nder! Hoppas det l�ser sig", "Vampirechick"));
		
	}
}
