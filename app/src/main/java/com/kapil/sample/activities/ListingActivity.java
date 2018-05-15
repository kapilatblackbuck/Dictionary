package com.kapil.sample.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.kapil.sample.CommonUtils;
import com.kapil.sample.R;
import com.kapil.sample.network.data.Scenario;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by kapilsharma on 11/07/17.
 */

public class ListingActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_scenario)
    TextView tvScenario;

    @BindView(R.id.view_answer)
    View answerView;


    @BindView(R.id.answer)
    EditText answer;

    @BindView(R.id.btn_show_answer)
    Button btnShowAnswer;

    @BindView(R.id.btn_okay)
    Button btnOkay;

    @BindView(R.id.btn_next)
    Button btnNext;


    @BindView(R.id.view_usage)
    View viewUsage;

    @BindView(R.id.tv_usage)
    TextView tvUsage;

    CompositeSubscription compositeSubscription;
    public ArrayList<Scenario> scenarios;
    private Scenario currentScenario;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        ButterKnife.bind(this);
        initializeToolbar(false, getString(R.string.app_title),true);
        fetchScenarios();
        compositeSubscription = new CompositeSubscription();
        btnOkay.setOnClickListener(this);
        btnShowAnswer.setOnClickListener(this);
        btnNext.setOnClickListener(this);


    }


    private void fetchScenarios() {
        Type listType = new TypeToken<ArrayList<Scenario>>(){}.getType();
       Subscription subscription = CommonUtils.readFromFile("word_bank.json",listType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<Scenario>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ArrayList<Scenario> scenarios) {
                        ListingActivity.this.scenarios = scenarios;
                        showScenarios();

                    }
                });

//       compositeSubscription.add(subscription);
    }

    private void showScenarios() {
        if (scenarios == null || scenarios.size() == 0) {
            Toast.makeText(this,"No scenario found", Toast.LENGTH_LONG).show();
            return;
        }
        int index = CommonUtils.getRandomNumberInRange(0, scenarios.size()-1);
        currentScenario = scenarios.get(index);
        scenarios.remove(index);
        viewUsage.setVisibility(View.GONE);
        answerView.setVisibility(View.VISIBLE);
        tvUsage.setText(currentScenario.getUsage());
        tvScenario.setText(currentScenario.getScenarios());

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_okay:
                onOkayClick();
                break;
            case R.id.btn_show_answer:
                onShowAnswerClick();
                break;
            case R.id.btn_next:
                onNextClick();
                break;
        }
    }

    private void onNextClick() {
        showScenarios();
    }

    private void onOkayClick() {
        if(currentScenario.answer.equalsIgnoreCase(answer.getText().toString())) {
            Toast.makeText(this,"Good", Toast.LENGTH_LONG).show();
            answer.setText("");
            onNextClick();
        } else {
            Toast.makeText(this,"wrong answer", Toast.LENGTH_LONG).show();
            onShowAnswerClick();
        }

    }

    private void onShowAnswerClick() {
        viewUsage.setVisibility(View.VISIBLE);
        answerView.setVisibility(View.GONE);
    }
}
