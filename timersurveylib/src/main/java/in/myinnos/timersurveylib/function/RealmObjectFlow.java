package in.myinnos.timersurveylib.function;

import in.myinnos.timersurveylib.models.RealmQuestionAnswersModel;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RealmObjectFlow {

    // The Realm file will be located in Context.getFilesDir() with name "myrealm.realm"
    public static RealmConfiguration config = new RealmConfiguration.Builder()
            .name("myinnos.realm")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(1)
            .build();
    // Use the config
    public static Realm realm = Realm.getInstance(config);


    public static void clearObject() {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<RealmQuestionAnswersModel> result = realm.where(RealmQuestionAnswersModel.class).findAll();
                result.deleteAllFromRealm();
            }
        });
    }

    public static void set(String question, String answer) {

        final RealmQuestionAnswersModel obj = new RealmQuestionAnswersModel();
        obj.setQuestion(question);
        obj.setAnswer(answer);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // This will create a new object in Realm or throw an exception if the
                // object already exists (same primary key)
                //realm.copyToRealm(obj);

                // This will update an existing object with the same primary key
                // or create a new object if an object with no primary key = 42
                realm.copyToRealmOrUpdate(obj);
            }
        });
    }

}
