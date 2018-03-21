package org.greenpad.vegist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

public class CourseActivity extends AppCompatActivity {

    private String course;
    private TextView abbr;
    private TextView title;
    private TextView level;
    private TextView school;
    private TextView department;
    private TextView credits;
    private TextView desc;
    private TextView prereq;
    private TextView coreq;
    private TextView antireq;
    private TextView last;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        abbr = findViewById(R.id.course_abbr);
        title = findViewById(R.id.course_title);
        level = findViewById(R.id.course_level);
        school = findViewById(R.id.course_school);
        department = findViewById(R.id.course_dment);
        credits = findViewById(R.id.course_cr);
        desc = findViewById(R.id.course_desc);
        prereq = findViewById(R.id.course_prereq);
        coreq = findViewById(R.id.course_coreq);
        antireq = findViewById(R.id.course_antireq);
        last = findViewById(R.id.course_last);



        course = getIntent().getStringExtra("data");

        try{
            JSONObject data = new JSONObject(course);

            title.setText(data.getString("TITLE"));
            abbr.setText(data.getString("ABBR"));
            level.setText(data.getString("ACADEMICLEVEL"));
            school.setText(data.getString("SCHOOL"));
            department.setText(data.getString("DEPARTMENT"));
            credits.setText(data.getString("CRUS") + " (" + data.getString("CRECTS") + ")");
            desc.setText(data.getString("SHORTDESC"));
            prereq.setText(data.getString("PREREQ"));
            coreq.setText(data.getString("COREQ"));
            antireq.setText(data.getString("ANTIREQ"));

            String l = data.getString("LASTTAUGHT");

            switch (l){
                case "321":
                    last.setText("Summer 2018");
                    break;

                case "301":
                    last.setText("Spring 2018");
                    break;

                case "281":
                    last.setText("EMBA 2017-2018");
                    break;

                case "261":
                    last.setText("Fall 2017");
                    break;

                case "241":
                    last.setText("Summer 2017");
                    break;

                case "223":
                    last.setText("Fall 2016");
                    break;

                case "222":
                    last.setText("SoM 2017-2021");
                    break;

                case "221":
                    last.setText("Fall 2016");
                    break;

                case "201":
                    last.setText("EMBA 2016-2017");
                    break;

                case "161":
                    last.setText("SoM 2016-2020");
                    break;

                default:
                    last.setText("n/a");

            }
        }catch (Exception e){
            Log.e("ERRR", e.toString());
        }

    }
}
