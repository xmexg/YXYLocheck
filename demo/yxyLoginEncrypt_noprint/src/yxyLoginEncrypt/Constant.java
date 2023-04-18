// jadx - package cn.ulearning.core;
package yxyLoginEncrypt;

/* loaded from: classes.dex */
public final class Constant {
    public static final String ACTION_MY_STORE_COURSES_UPDATE = "cn.ulearning.yxy.action.mystorecourseupdate";
    public static final String ACTION_SYNC_COURSE = "cn.ulearning.yxy.action.sync_course";
    public static final String AGREEMENT_KEY = "user_agreement_key";
    public static final long CACHE_FILE_LIMIT_TIME = 1209600000;
    public static final String CORDOVA_FILE = "cdvfile://umooc_cordova";
    public static final String COURSE_APP_ASSESS = "course_app_assess";
    public static final String COURSE_APP_CLASS = "course_app_class";
    public static final String COURSE_APP_DISCUSS = "course_app_discuss";
    public static final String COURSE_APP_EXAM = "course_app_exam";
    public static final String COURSE_APP_LIVE = "course_app_live";
    public static final String COURSE_APP_MEMBER = "course_app_member";
    public static final String COURSE_APP_RESOURCE = "course_app_resource";
    public static final String COURSE_APP_SPOKEN = "course_app_spoken";
    public static final String COURSE_APP_TEXTBOOK = "course_app_textbook";
    public static final String COURSE_APP_UNITS = "course_app_units";
    public static final String COURSE_APP_WORDS = "course_app_words";
    public static final String COURSE_APP_WORK = "course_app_work";
    public static final String COURSE_LEARN_IMAGE_ACTIVITY_IMAGE_STRING = "CourseLearnImageActivityImageString";
    public static final long DEFAULT_EXPIRY_TIME = 60000;
    public static final String DEFAULT_PWD = "ulearning";
    public static final String ERROR = "error";
    public static final String FILE_PATH = "filepath";
    public static final String GENERIC_ACTIVITY_COURSE_LESSON_POSITION_INT = "GenericActivityCourseLessonPositionInt";
    public static final String GENERIC_ACTIVITY_COURSE_LESSON_SECTION_PAGE_POSITION_INT = "GENERIC_ACTIVITY_COURSE_LESSON_SECTION_PAGE_POSITION_INT";
    public static final String GENERIC_ACTIVITY_COURSE_LESSON_SECTION_POSITION_INT = "GenericActivityCourseLessonSectionPositionInt";
    public static final int PAGESIZE = 20;
//    public static final String PLAYER_PATH = MainApplication.getInstance().fileDir + "h5/coursePlayer";
    public static final String POST_FAIL = "fail";
    public static final int RECORD_AUDIO_SIZE = 300;
    public static final int RECORD_MSG_SIZE = 60000;
    public static final int REQUEST_ACTIVE_TEXTBOOK = 13;
    public static final int REQUEST_MODIFY_COURSE = 14;
    public static final int REQUEST_PERMISSION_AUDIO = 12;
    public static final int REQUEST_PERMISSION_CAMERA = 11;
    public static final int REQUEST_PERMISSION_EXTERNAL = 13;
    public static final int REQUEST_PERMISSION_READ_PHONE_STATE = 14;
    public static final String STATUS_KEY = "status";
    public static final String SUCCESS = "success";
    public static final int TEXTSIZE = 500;
    public static final String TIMESTAMP = "timestamp";
    public static final int TWO_DAY = 10368000;

    /* loaded from: classes.dex */
    public static final class DOWNLOAD {
        public static final int ERROR = 0;
        public static final int FINISH = 1;
        public static final int NORMAL = -1;
        public static final int PAUSE = 3;
        public static final int PROCESS = 2;
    }

    /* loaded from: classes.dex */
    public static final class HANLDER_WHAT {
        public static final int FAIL = 0;
        public static final int SUCCESS = 1;
    }

    /* loaded from: classes.dex */
    public static final class RegString {
        public static final String FILL_REG = ":|!|\\.|\"|\\(|\\)|\\*|\\+|,|-|<|>|=|\\?|\\[|\\]|\\^|_|\\{|\\}|~|`|\\s|‘|’|。|“|”|，|＇|，|。|？|！|：|、|@|……|“|”|；|‘|’|～|\\.|-|（|）|《|》|〈|〉|〔|〕|\\*|\\&|\\［|\\］|【|】|——|｀|#|￥|\\%|ˇ|•|\\+|=|\\｛|\\｝|ˉ|¨|．|｜|〃|\\‖|々|「|」|『|』|〖|〗|∶|＇|＂|／|＊|＆|＼|＃|＄|％|︿|＿|＋|－|＝|＜| |'|\\p{Blank}";
    }

    /* loaded from: classes.dex */
    public static final class STATUS {
        public static final int ALL = 0;
        public static final int DISABLED = 2;
        public static final int PROGRESS = 1;
    }
}