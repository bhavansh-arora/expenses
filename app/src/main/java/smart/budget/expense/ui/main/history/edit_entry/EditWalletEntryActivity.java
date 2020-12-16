package smart.budget.expense.ui.main.history.edit_entry;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import smart.budget.expense.base.BaseActivity;
import smart.budget.expense.exceptions.EmptyStringException;
import smart.budget.expense.exceptions.ZeroBalanceDifferenceException;
import smart.budget.expense.firebase.FirebaseElement;
import smart.budget.expense.firebase.FirebaseObserver;
import smart.budget.expense.firebase.viewmodel_factories.UserProfileViewModelFactory;
import smart.budget.expense.firebase.models.User;
import smart.budget.expense.firebase.viewmodel_factories.WalletEntryViewModelFactory;
import smart.budget.expense.util.CategoriesHelper;
import smart.budget.expense.models.Category;
import smart.budget.expense.ui.add_entry.EntryCategoriesAdapter;
import smart.budget.expense.ui.add_entry.EntryTypeListViewModel;
import smart.budget.expense.ui.add_entry.EntryTypesAdapter;
import smart.budget.expense.util.CurrencyHelper;
import smart.budget.expense.R;
import smart.budget.expense.firebase.models.WalletEntry;

public class EditWalletEntryActivity extends BaseActivity {

    private Spinner selectCategorySpinner;
    private TextInputEditText selectNameEditText;
    private Calendar choosedDate;
    private TextInputEditText selectAmountEditText;
    private TextInputEditText selectMobileEditText;
    private TextInputEditText selectVillageEditText;
    private TextInputEditText selectDescriptionEditText;
    private TextView chooseDayTextView;
    private TextView chooseTimeTextView;
    private Spinner selectTypeSpinner;
    private User user;
    private WalletEntry walletEntry;
    private Button removeEntryButton;
    private Button editEntryButton;
    private String walletId;
    private TextInputLayout selectAmountInputLayout;
    private TextInputLayout selectNameInputLayout;
    private TextInputLayout selectMobileInputLayout;
    private TextInputLayout selectVillageInputLayout;
    private TextInputLayout selectDescriptionInputLayout;
    private LinearLayout week1,week2,week3,week4;
    private CheckBox one,two,three,four,five,six,seven,eight,nine,ten,eleven,twelve,thirteen,fourteen,fifteen,sixteen,seventeen,eighteen,nineteen,twenty,twentyone,twentytwo,twentythree,twentyfour,twentyfive,twentysix,twentyseven,twentyeight,twentynine,thirty,thirtyone,thirtytwo,thirtythree,thirtyfour,thirtyfive,thirtysix,thirtyseven,thirtyeight,thirtynine,forty,fortyone,fortytwo,fortythree,fortyfour,fortyfive,fortysix,fortyseven,fortyeight;
private int install=0;
private int install_duration=0;
private String city="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_wallet_entry);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit wallet entry");


        walletId = getIntent().getExtras().getString("wallet-entry-id");
week1=findViewById(R.id.week1);
week2=findViewById(R.id.week2);
week3=findViewById(R.id.week3);
week4=findViewById(R.id.week4);
        selectCategorySpinner = findViewById(R.id.select_category_spinner);
        selectNameEditText = findViewById(R.id.select_name_edittext);
        selectMobileEditText = findViewById(R.id.select_mobile_edittext);
        selectAmountEditText = findViewById(R.id.select_amount_edittext);
        selectVillageEditText = findViewById(R.id.select_village_edittext);
        selectDescriptionEditText = findViewById(R.id.select_description_edittext);
        selectNameInputLayout = findViewById(R.id.select_name_inputlayout);
        selectTypeSpinner = findViewById(R.id.select_type_spinner);
        editEntryButton = findViewById(R.id.edit_entry_button);
        removeEntryButton = findViewById(R.id.remove_entry_button);
        chooseTimeTextView = findViewById(R.id.choose_time_textview);
        chooseDayTextView = findViewById(R.id.choose_day_textview);
        selectAmountInputLayout = findViewById(R.id.select_amount_inputlayout);
        selectMobileInputLayout = findViewById(R.id.select_mobile_inputlayout);
        selectVillageInputLayout = findViewById(R.id.select_village_inputlayout);
        selectDescriptionInputLayout = findViewById(R.id.select_description_inputlayout);
        one=findViewById(R.id.one);
        two=findViewById(R.id.two);
        three=findViewById(R.id.three);
        four=findViewById(R.id.four);
        five=findViewById(R.id.five);
        six=findViewById(R.id.six);
        seven=findViewById(R.id.seven);
        eight=findViewById(R.id.eight);
        nine=findViewById(R.id.nine);
        ten=findViewById(R.id.ten);
        eleven=findViewById(R.id.eleven);
        twelve=findViewById(R.id.twelve);
        thirteen=findViewById(R.id.thirteen);
        fourteen=findViewById(R.id.fourteen);
        fifteen=findViewById(R.id.fifteen);
        sixteen=findViewById(R.id.sixteen);
        seventeen=findViewById(R.id.seventeen);
        eighteen=findViewById(R.id.eighteen);
        nineteen=findViewById(R.id.nineteen);
        twenty=findViewById(R.id.twenty);
        twentyone=findViewById(R.id.twentyone);
        twentytwo=findViewById(R.id.twentytwo);
        twentythree=findViewById(R.id.twentythree);
        twentyfour=findViewById(R.id.twentyfour);
        twentyfive=findViewById(R.id.twentyfive);
        twentysix=findViewById(R.id.twentysix);
        twentyseven=findViewById(R.id.twentyseven);
        twentyeight=findViewById(R.id.twentyeight);
        twentynine=findViewById(R.id.twentynine);
        thirty=findViewById(R.id.thirty);
        thirtyone=findViewById(R.id.thirtyone);
        thirtytwo=findViewById(R.id.thirtytwo);
        thirtythree=findViewById(R.id.thirtythree);
        thirtyfour=findViewById(R.id.thirtyfour);
        thirtyfive=findViewById(R.id.thirtyfive);
        thirtysix=findViewById(R.id.thirtysix);
        thirtyseven=findViewById(R.id.thirtyseven);
        thirtyeight=findViewById(R.id.thirtyeight);
        thirtynine=findViewById(R.id.thirtynine);
        forty=findViewById(R.id.forty);
        fortyone=findViewById(R.id.fortyone);
        fortytwo=findViewById(R.id.fortytwo);
        fortythree=findViewById(R.id.fortythree);
        fortyfour=findViewById(R.id.fortyfour);
        fortyfive=findViewById(R.id.fortyfive);
        fortysix=findViewById(R.id.fortysix);
        fortyseven=findViewById(R.id.fortyseven);
        fortyeight=findViewById(R.id.fortyeight);


        choosedDate = Calendar.getInstance();

        EntryTypesAdapter typeAdapter = new EntryTypesAdapter(this,
                R.layout.new_entry_type_spinner_row, Arrays.asList(
                new EntryTypeListViewModel("Expense", Color.parseColor("#ef5350"),
                        R.drawable.money_icon),
                new EntryTypeListViewModel("Income", Color.parseColor("#66bb6a"),
                        R.drawable.money_icon)));

        selectTypeSpinner.setAdapter(typeAdapter);

        updateDate();
        chooseDayTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDate();
            }
        });
        chooseTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickTime();
            }
        });



        editEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    editWalletEntry(((selectTypeSpinner.getSelectedItemPosition() * 2) - 1) *
                                    CurrencyHelper.convertAmountStringToLong(selectAmountEditText.getText().toString()),
                            choosedDate.getTime(),
                            ((Category) selectCategorySpinner.getSelectedItem()).getCategoryID(),
                            selectNameEditText.getText().toString(),
                            selectMobileEditText.getText().toString(), selectVillageEditText.getText().toString(), selectDescriptionEditText.getText().toString());
                }  catch (EmptyStringException e) {
                    selectNameInputLayout.setError(e.getMessage());
                    selectVillageInputLayout.setError(e.getMessage());
                    selectDescriptionInputLayout.setError(e.getMessage());
                } catch (ZeroBalanceDifferenceException e) {
                    selectAmountInputLayout.setError(e.getMessage());
                }
            }
        });

        removeEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRemoveWalletEntryDialog();
            }

            public void showRemoveWalletEntryDialog() {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditWalletEntryActivity.this);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removeWalletEntry();
                    }
                }).setNegativeButton("No", null).show();

            }
        });



        UserProfileViewModelFactory.getModel(getUid(), this).observe(this, new FirebaseObserver<FirebaseElement<User>>() {
            @Override
            public void onChanged(FirebaseElement<User> firebaseElement) {
                if (firebaseElement.hasNoError()) {
                    user = firebaseElement.getElement();
                    dataUpdated();
                }
            }
        });


        WalletEntryViewModelFactory.getModel(getUid(), walletId, this).observe(this, new FirebaseObserver<FirebaseElement<WalletEntry>>() {
            @Override
            public void onChanged(FirebaseElement<WalletEntry> firebaseElement) {
                if (firebaseElement.hasNoError()) {
                    walletEntry = firebaseElement.getElement();
                    dataUpdated();
                }
            }
        });


    }

    public void dataUpdated() {

        if (walletEntry == null || user == null) return;
        install_duration = walletEntry.duration;
        city = walletEntry.city;
if(walletEntry.duration==12){
    week1.setVisibility(View.VISIBLE);
    System.out.println(12);
}
       else if(walletEntry.duration==24){
            week1.setVisibility(View.VISIBLE);
    week2.setVisibility(View.VISIBLE);
    System.out.println(24);
        }
         else if(walletEntry.duration==36){
            week1.setVisibility(View.VISIBLE);
    week2.setVisibility(View.VISIBLE);
    week3.setVisibility(View.VISIBLE);
    System.out.println(36);
        }
        else if(walletEntry.duration==48){
            week1.setVisibility(View.VISIBLE);
    week2.setVisibility(View.VISIBLE);
    week3.setVisibility(View.VISIBLE);
    week4.setVisibility(View.VISIBLE);
    System.out.println(48);
        }
        if(walletEntry.installment==1)
            one(one);
       else if(walletEntry.installment==2)
            two(two);
        else if(walletEntry.installment==3)
            three(three);
        else if(walletEntry.installment==4)
            four(four);
        else if(walletEntry.installment==5)
            five(five);
        else if(walletEntry.installment==6)
            six(six);
        else if(walletEntry.installment==7)
            seven(seven);
        else if(walletEntry.installment==8)
            eight(eight);
        else if(walletEntry.installment==9)
            nine(nine);
        else if(walletEntry.installment==10)
            ten(ten);
        else if(walletEntry.installment==11)
            eleven(eleven);
        else if(walletEntry.installment==12)
            twelve(twelve);
        else if(walletEntry.installment==13)
            thirteen(thirteen);
        else if(walletEntry.installment==14)
            fourteen(fourteen);
        else if(walletEntry.installment==15)
            fifteen(fifteen);
        else if(walletEntry.installment==16)
            sixteen(sixteen);
        else if(walletEntry.installment==17)
            seventeen(seventeen);
        else if(walletEntry.installment==18)
            eighteen(eighteen);
        else if(walletEntry.installment==19)
            nineteen(nineteen);
        else if(walletEntry.installment==20)
            twenty(twenty);
        else if(walletEntry.installment==21)
            twentyone(twentyone);
        else if(walletEntry.installment==22)
            twentytwo(twentytwo);
        else if(walletEntry.installment==23)
            twentythree(twentythree);
        else if(walletEntry.installment==24)
            twentyfour(twentyfour);
        else if(walletEntry.installment==25)
            twentyfive(twentyfive);
        else if(walletEntry.installment==26)
            twentysix(twentysix);
        else if(walletEntry.installment==27)
            twentyseven(twentyseven);
        else if(walletEntry.installment==28)
            twentyeight(twentyeight);
        else if(walletEntry.installment==29)
            twentynine(twentynine);
        else if(walletEntry.installment==30)
            thirty(thirty);
        else if(walletEntry.installment==31)
            thirtyone(thirtyone);
        else if(walletEntry.installment==32)
            thirtytwo(thirtytwo);
        else if(walletEntry.installment==33)
            thirtythree(thirtythree);
           else if(walletEntry.installment==34)
            thirtyfour(thirtyfour);
        else if(walletEntry.installment==35)
            thirtyfive(thirtyfive);
        else if(walletEntry.installment==36)
            thirtysix(thirtysix);
        else if(walletEntry.installment==37)
            thirtyseven(thirtyseven);
        else if(walletEntry.installment==38)
            thirtyeight(thirtyeight);
        else if(walletEntry.installment==39)
            thirtynine(thirtynine);
        else if(walletEntry.installment==40)
            forty(forty);
        else if(walletEntry.installment==41)
            fortyone(fortyone);
        else if(walletEntry.installment==42)
            fortytwo(fortytwo);
        else if(walletEntry.installment==43)
            fortythree(fortythree);
        else if(walletEntry.installment==44)
            fortyfour(fortyfour);
        else if(walletEntry.installment==45)
            fortyfive(fortyfive);
        else if(walletEntry.installment==46)
            fortysix(fortysix);
        else if(walletEntry.installment==47)
            fortyseven(fortyseven);
        else if(walletEntry.installment==48)
            fortyeight(fortyeight);



















































        final List<Category> categories = CategoriesHelper.getCategories(user);
        EntryCategoriesAdapter categoryAdapter = new EntryCategoriesAdapter(this,
                R.layout.new_entry_type_spinner_row, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectCategorySpinner.setAdapter(categoryAdapter);

        CurrencyHelper.setupAmountEditText(selectAmountEditText, user);
        choosedDate.setTimeInMillis(-walletEntry.timestamp);
        updateDate();
        selectNameEditText.setText(walletEntry.name);
        selectMobileEditText.setText(walletEntry.mobile);
        selectVillageEditText.setText(walletEntry.village);
        selectDescriptionEditText.setText(walletEntry.description);


        selectTypeSpinner.post(new Runnable() {
            @Override
            public void run() {
                if (walletEntry.balanceDifference < 0) selectTypeSpinner.setSelection(0);
                else selectTypeSpinner.setSelection(1);            }
        });

        selectCategorySpinner.post(new Runnable() {
            @Override
            public void run() {
                EntryCategoriesAdapter adapter = (EntryCategoriesAdapter) selectCategorySpinner.getAdapter();
                selectCategorySpinner.setSelection(adapter.getItemIndex(walletEntry.categoryID));
            }
        });


        long amount = Math.abs(walletEntry.balanceDifference);
        String current = CurrencyHelper.formatCurrency(user.currency, amount);
        selectAmountEditText.setText(current);
        selectAmountEditText.setSelection(current.length() -
                (user.currency.left ? 0 : (user.currency.symbol.length() + (user.currency.space ? 1 : 0))));

    }


    private void updateDate() {

        SimpleDateFormat dataFormatter = new SimpleDateFormat("yyyy-MM-dd");
        chooseDayTextView.setText(dataFormatter.format(choosedDate.getTime()));

        SimpleDateFormat dataFormatter2 = new SimpleDateFormat("HH:mm");
        chooseTimeTextView.setText(dataFormatter2.format(choosedDate.getTime()));
    }

    public void editWalletEntry(long balanceDifference, Date entryDate, String entryCategory, String entryName, String mobile , String village , String description) throws EmptyStringException, ZeroBalanceDifferenceException {
        if (balanceDifference == 0) {
            throw new ZeroBalanceDifferenceException("Balance difference should not be 0");
        }

        if (entryName == null || entryName.length() == 0) {
            throw new EmptyStringException("Entry name length should be > 0");
        }

        long finalBalanceDifference = balanceDifference - walletEntry.balanceDifference;
        user.wallet.sum += finalBalanceDifference;
        UserProfileViewModelFactory.saveModel(getUid(), user);

        FirebaseDatabase.getInstance().getReference().child("wallet-entries").child(getUid())
                .child("default").child(walletId).setValue(new WalletEntry(entryCategory, entryName, entryDate.getTime(), balanceDifference ,mobile , village , description,city,install_duration,install));
        finish();
    }

    public void removeWalletEntry() {
        user.wallet.sum -= walletEntry.balanceDifference;
        UserProfileViewModelFactory.saveModel(getUid(), user);

        FirebaseDatabase.getInstance().getReference().child("wallet-entries").child(getUid())
                .child("default").child(walletId).removeValue();
        finish();
    }


    private void pickTime() {
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                choosedDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                choosedDate.set(Calendar.MINUTE, minute);
                updateDate();

            }
        }, choosedDate.get(Calendar.HOUR_OF_DAY), choosedDate.get(Calendar.MINUTE), true).show();
    }


    private void pickDate() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        choosedDate.set(year, monthOfYear, dayOfMonth);
                        updateDate();

                    }
                }, year, month, day).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        onBackPressed();
        return true;
    }
    public void fortyeight(View view) {
        install = 48;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(true);
        thirty.setChecked(true);
        thirtyone.setChecked(true);
        thirtytwo.setChecked(true);
        thirtythree.setChecked(true);
        thirtyfour.setChecked(true);
        thirtyfive.setChecked(true);
        thirtysix.setChecked(true);
        thirtyseven.setChecked(true);
        thirtyeight.setChecked(true);
        thirtynine.setChecked(true);
        forty.setChecked(true);
        fortyone.setChecked(true);
        fortytwo.setChecked(true);
        fortythree.setChecked(true);
        fortyfour.setChecked(true);
        fortyfive.setChecked(true);
        fortysix.setChecked(true);
        fortyseven.setChecked(true);
        fortyeight.setChecked(true);
    }

    public void fortyseven(View view) {
        install = 47;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(true);
        thirty.setChecked(true);
        thirtyone.setChecked(true);
        thirtytwo.setChecked(true);
        thirtythree.setChecked(true);
        thirtyfour.setChecked(true);
        thirtyfive.setChecked(true);
        thirtysix.setChecked(true);
        thirtyseven.setChecked(true);
        thirtyeight.setChecked(true);
        thirtynine.setChecked(true);
        forty.setChecked(true);
        fortyone.setChecked(true);
        fortytwo.setChecked(true);
        fortythree.setChecked(true);
        fortyfour.setChecked(true);
        fortyfive.setChecked(true);
        fortysix.setChecked(true);
        fortyseven.setChecked(true);
        fortyeight.setChecked(false);
    }

    public void fortysix(View view) {
        install = 46;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(true);
        thirty.setChecked(true);
        thirtyone.setChecked(true);
        thirtytwo.setChecked(true);
        thirtythree.setChecked(true);
        thirtyfour.setChecked(true);
        thirtyfive.setChecked(true);
        thirtysix.setChecked(true);
        thirtyseven.setChecked(true);
        thirtyeight.setChecked(true);
        thirtynine.setChecked(true);
        forty.setChecked(true);
        fortyone.setChecked(true);
        fortytwo.setChecked(true);
        fortythree.setChecked(true);
        fortyfour.setChecked(true);
        fortyfive.setChecked(true);
        fortysix.setChecked(true);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void one(View view) {
        install = 1;
       one.setChecked(true);
        two.setChecked(false);
        three.setChecked(false);
        four.setChecked(false);
        five.setChecked(false);
        six.setChecked(false);
        seven.setChecked(false);
        eight.setChecked(false);
        nine.setChecked(false);
        ten.setChecked(false);
        eleven.setChecked(false);
        twelve.setChecked(false);
        thirteen.setChecked(false);
        fourteen.setChecked(false);
        fifteen.setChecked(false);
        sixteen.setChecked(false);
        seventeen.setChecked(false);
        eighteen.setChecked(false);
        nineteen.setChecked(false);
        twenty.setChecked(false);
        twentyone.setChecked(false);
        twentytwo.setChecked(false);
        twentythree.setChecked(false);
        twentyfour.setChecked(false);
        twentyfive.setChecked(false);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);













    }

    public void two(View view) {
        install = 2;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(false);
        four.setChecked(false);
        five.setChecked(false);
        six.setChecked(false);
        seven.setChecked(false);
        eight.setChecked(false);
        nine.setChecked(false);
        ten.setChecked(false);
        eleven.setChecked(false);
        twelve.setChecked(false);
        thirteen.setChecked(false);
        fourteen.setChecked(false);
        fifteen.setChecked(false);
        sixteen.setChecked(false);
        seventeen.setChecked(false);
        eighteen.setChecked(false);
        nineteen.setChecked(false);
        twenty.setChecked(false);
        twentyone.setChecked(false);
        twentytwo.setChecked(false);
        twentythree.setChecked(false);
        twentyfour.setChecked(false);
        twentyfive.setChecked(false);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void three(View view) {
        install = 3;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(false);
        five.setChecked(false);
        six.setChecked(false);
        seven.setChecked(false);
        eight.setChecked(false);
        nine.setChecked(false);
        ten.setChecked(false);
        eleven.setChecked(false);
        twelve.setChecked(false);
        thirteen.setChecked(false);
        fourteen.setChecked(false);
        fifteen.setChecked(false);
        sixteen.setChecked(false);
        seventeen.setChecked(false);
        eighteen.setChecked(false);
        nineteen.setChecked(false);
        twenty.setChecked(false);
        twentyone.setChecked(false);
        twentytwo.setChecked(false);
        twentythree.setChecked(false);
        twentyfour.setChecked(false);
        twentyfive.setChecked(false);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void four(View view) {
        install = 4;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(false);
        six.setChecked(false);
        seven.setChecked(false);
        eight.setChecked(false);
        nine.setChecked(false);
        ten.setChecked(false);
        eleven.setChecked(false);
        twelve.setChecked(false);
        thirteen.setChecked(false);
        fourteen.setChecked(false);
        fifteen.setChecked(false);
        sixteen.setChecked(false);
        seventeen.setChecked(false);
        eighteen.setChecked(false);
        nineteen.setChecked(false);
        twenty.setChecked(false);
        twentyone.setChecked(false);
        twentytwo.setChecked(false);
        twentythree.setChecked(false);
        twentyfour.setChecked(false);
        twentyfive.setChecked(false);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void five(View view) {
        install = 5;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(false);
        seven.setChecked(false);
        eight.setChecked(false);
        nine.setChecked(false);
        ten.setChecked(false);
        eleven.setChecked(false);
        twelve.setChecked(false);
        thirteen.setChecked(false);
        fourteen.setChecked(false);
        fifteen.setChecked(false);
        sixteen.setChecked(false);
        seventeen.setChecked(false);
        eighteen.setChecked(false);
        nineteen.setChecked(false);
        twenty.setChecked(false);
        twentyone.setChecked(false);
        twentytwo.setChecked(false);
        twentythree.setChecked(false);
        twentyfour.setChecked(false);
        twentyfive.setChecked(false);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void six(View view) {
        install = 6;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(false);
        eight.setChecked(false);
        nine.setChecked(false);
        ten.setChecked(false);
        eleven.setChecked(false);
        twelve.setChecked(false);
        thirteen.setChecked(false);
        fourteen.setChecked(false);
        fifteen.setChecked(false);
        sixteen.setChecked(false);
        seventeen.setChecked(false);
        eighteen.setChecked(false);
        nineteen.setChecked(false);
        twenty.setChecked(false);
        twentyone.setChecked(false);
        twentytwo.setChecked(false);
        twentythree.setChecked(false);
        twentyfour.setChecked(false);
        twentyfive.setChecked(false);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void seven(View view) {
        install = 7;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(false);
        nine.setChecked(false);
        ten.setChecked(false);
        eleven.setChecked(false);
        twelve.setChecked(false);
        thirteen.setChecked(false);
        fourteen.setChecked(false);
        fifteen.setChecked(false);
        sixteen.setChecked(false);
        seventeen.setChecked(false);
        eighteen.setChecked(false);
        nineteen.setChecked(false);
        twenty.setChecked(false);
        twentyone.setChecked(false);
        twentytwo.setChecked(false);
        twentythree.setChecked(false);
        twentyfour.setChecked(false);
        twentyfive.setChecked(false);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void eight(View view) {
        install =8;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(false);
        ten.setChecked(false);
        eleven.setChecked(false);
        twelve.setChecked(false);
        thirteen.setChecked(false);
        fourteen.setChecked(false);
        fifteen.setChecked(false);
        sixteen.setChecked(false);
        seventeen.setChecked(false);
        eighteen.setChecked(false);
        nineteen.setChecked(false);
        twenty.setChecked(false);
        twentyone.setChecked(false);
        twentytwo.setChecked(false);
        twentythree.setChecked(false);
        twentyfour.setChecked(false);
        twentyfive.setChecked(false);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void nine(View view) {
        install = 9;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(false);
        eleven.setChecked(false);
        twelve.setChecked(false);
        thirteen.setChecked(false);
        fourteen.setChecked(false);
        fifteen.setChecked(false);
        sixteen.setChecked(false);
        seventeen.setChecked(false);
        eighteen.setChecked(false);
        nineteen.setChecked(false);
        twenty.setChecked(false);
        twentyone.setChecked(false);
        twentytwo.setChecked(false);
        twentythree.setChecked(false);
        twentyfour.setChecked(false);
        twentyfive.setChecked(false);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void ten(View view) {
        install = 10;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(false);
        twelve.setChecked(false);
        thirteen.setChecked(false);
        fourteen.setChecked(false);
        fifteen.setChecked(false);
        sixteen.setChecked(false);
        seventeen.setChecked(false);
        eighteen.setChecked(false);
        nineteen.setChecked(false);
        twenty.setChecked(false);
        twentyone.setChecked(false);
        twentytwo.setChecked(false);
        twentythree.setChecked(false);
        twentyfour.setChecked(false);
        twentyfive.setChecked(false);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void eleven(View view) {
        install = 11;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(false);
        thirteen.setChecked(false);
        fourteen.setChecked(false);
        fifteen.setChecked(false);
        sixteen.setChecked(false);
        seventeen.setChecked(false);
        eighteen.setChecked(false);
        nineteen.setChecked(false);
        twenty.setChecked(false);
        twentyone.setChecked(false);
        twentytwo.setChecked(false);
        twentythree.setChecked(false);
        twentyfour.setChecked(false);
        twentyfive.setChecked(false);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);

    }

    public void twelve(View view) {
        install = 12;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(false);
        fourteen.setChecked(false);
        fifteen.setChecked(false);
        sixteen.setChecked(false);
        seventeen.setChecked(false);
        eighteen.setChecked(false);
        nineteen.setChecked(false);
        twenty.setChecked(false);
        twentyone.setChecked(false);
        twentytwo.setChecked(false);
        twentythree.setChecked(false);
        twentyfour.setChecked(false);
        twentyfive.setChecked(false);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void thirteen(View view) {
        install = 13;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(false);
        fifteen.setChecked(false);
        sixteen.setChecked(false);
        seventeen.setChecked(false);
        eighteen.setChecked(false);
        nineteen.setChecked(false);
        twenty.setChecked(false);
        twentyone.setChecked(false);
        twentytwo.setChecked(false);
        twentythree.setChecked(false);
        twentyfour.setChecked(false);
        twentyfive.setChecked(false);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void fourteen(View view) {
        install = 14;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(false);
        sixteen.setChecked(false);
        seventeen.setChecked(false);
        eighteen.setChecked(false);
        nineteen.setChecked(false);
        twenty.setChecked(false);
        twentyone.setChecked(false);
        twentytwo.setChecked(false);
        twentythree.setChecked(false);
        twentyfour.setChecked(false);
        twentyfive.setChecked(false);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void fifteen(View view) {
        install = 15;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(false);
        seventeen.setChecked(false);
        eighteen.setChecked(false);
        nineteen.setChecked(false);
        twenty.setChecked(false);
        twentyone.setChecked(false);
        twentytwo.setChecked(false);
        twentythree.setChecked(false);
        twentyfour.setChecked(false);
        twentyfive.setChecked(false);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void sixteen(View view) {
        install = 16;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(false);
        eighteen.setChecked(false);
        nineteen.setChecked(false);
        twenty.setChecked(false);
        twentyone.setChecked(false);
        twentytwo.setChecked(false);
        twentythree.setChecked(false);
        twentyfour.setChecked(false);
        twentyfive.setChecked(false);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void seventeen(View view) {
        install = 17;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(false);
        nineteen.setChecked(false);
        twenty.setChecked(false);
        twentyone.setChecked(false);
        twentytwo.setChecked(false);
        twentythree.setChecked(false);
        twentyfour.setChecked(false);
        twentyfive.setChecked(false);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void eighteen(View view) {
        install = 18;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(false);
        twenty.setChecked(false);
        twentyone.setChecked(false);
        twentytwo.setChecked(false);
        twentythree.setChecked(false);
        twentyfour.setChecked(false);
        twentyfive.setChecked(false);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void nineteen(View view) {
        install = 19;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(false);
        twentyone.setChecked(false);
        twentytwo.setChecked(false);
        twentythree.setChecked(false);
        twentyfour.setChecked(false);
        twentyfive.setChecked(false);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void twenty(View view) {
        install = 20;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(false);
        twentytwo.setChecked(false);
        twentythree.setChecked(false);
        twentyfour.setChecked(false);
        twentyfive.setChecked(false);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void twentyone(View view) {
        install = 21;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(true);
        thirty.setChecked(true);
        thirtyone.setChecked(true);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void twentytwo(View view) {
        install = 22;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(false);
        twentyfour.setChecked(false);
        twentyfive.setChecked(false);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void twentythree(View view) {
        install = 23;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(false);
        twentyfive.setChecked(false);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void twentyfour(View view) {
        install = 24;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(true);
        thirty.setChecked(true);
        thirtyone.setChecked(true);
        thirtytwo.setChecked(true);
        thirtythree.setChecked(true);
        thirtyfour.setChecked(true);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void twentyfive(View view) {
        install = 25;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(false);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void twentysix(View view) {
        install = 26;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(false);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void twentyseven(View view) {
        install = 27;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(false);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void twentyeight(View view) {
        install = 28;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(false);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void twentynine(View view) {
        install = 29;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(true);
        thirty.setChecked(false);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void thirty(View view) {
        install = 30;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(true);
        thirty.setChecked(true);
        thirtyone.setChecked(false);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void thirtyone(View view) {
        install = 31;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(true);
        thirty.setChecked(true);
        thirtyone.setChecked(true);
        thirtytwo.setChecked(false);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void thirtytwo(View view) {
        install = 32;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(true);
        thirty.setChecked(true);
        thirtyone.setChecked(true);
        thirtytwo.setChecked(true);
        thirtythree.setChecked(false);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void thirtythree(View view) {
        install = 33;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(true);
        thirty.setChecked(true);
        thirtyone.setChecked(true);
        thirtytwo.setChecked(true);
        thirtythree.setChecked(true);
        thirtyfour.setChecked(false);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void thirtyfour(View view) {
        install = 34;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(true);
        thirty.setChecked(true);
        thirtyone.setChecked(true);
        thirtytwo.setChecked(true);
        thirtythree.setChecked(true);
        thirtyfour.setChecked(true);
        thirtyfive.setChecked(false);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);

    }

    public void thirtyfive(View view) {
        install = 35;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(true);
        thirty.setChecked(true);
        thirtyone.setChecked(true);
        thirtytwo.setChecked(true);
        thirtythree.setChecked(true);
        thirtyfour.setChecked(true);
        thirtyfive.setChecked(true);
        thirtysix.setChecked(false);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void thirtysix(View view) {
        install = 36;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(true);
        thirty.setChecked(true);
        thirtyone.setChecked(true);
        thirtytwo.setChecked(true);
        thirtythree.setChecked(true);
        thirtyfour.setChecked(true);
        thirtyfive.setChecked(true);
        thirtysix.setChecked(true);
        thirtyseven.setChecked(false);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void thirtyseven(View view) {
        install = 37;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(true);
        thirty.setChecked(true);
        thirtyone.setChecked(true);
        thirtytwo.setChecked(true);
        thirtythree.setChecked(true);
        thirtyfour.setChecked(true);
        thirtyfive.setChecked(true);
        thirtysix.setChecked(true);
        thirtyseven.setChecked(true);
        thirtyeight.setChecked(false);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void thirtyeight(View view) {
        install = 38;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(true);
        thirty.setChecked(true);
        thirtyone.setChecked(true);
        thirtytwo.setChecked(true);
        thirtythree.setChecked(true);
        thirtyfour.setChecked(true);
        thirtyfive.setChecked(true);
        thirtysix.setChecked(true);
        thirtyseven.setChecked(true);
        thirtyeight.setChecked(true);
        thirtynine.setChecked(false);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void thirtynine(View view) {
        install = 39;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(true);
        thirty.setChecked(true);
        thirtyone.setChecked(true);
        thirtytwo.setChecked(true);
        thirtythree.setChecked(true);
        thirtyfour.setChecked(true);
        thirtyfive.setChecked(true);
        thirtysix.setChecked(true);
        thirtyseven.setChecked(true);
        thirtyeight.setChecked(true);
        thirtynine.setChecked(true);
        forty.setChecked(false);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void forty(View view) {
        install = 40;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(true);
        thirty.setChecked(true);
        thirtyone.setChecked(true);
        thirtytwo.setChecked(true);
        thirtythree.setChecked(true);
        thirtyfour.setChecked(true);
        thirtyfive.setChecked(true);
        thirtysix.setChecked(true);
        thirtyseven.setChecked(true);
        thirtyeight.setChecked(true);
        thirtynine.setChecked(true);
        forty.setChecked(true);
        fortyone.setChecked(false);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void fortyone(View view) {
        install = 41;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(true);
        thirty.setChecked(true);
        thirtyone.setChecked(true);
        thirtytwo.setChecked(true);
        thirtythree.setChecked(true);
        thirtyfour.setChecked(true);
        thirtyfive.setChecked(true);
        thirtysix.setChecked(true);
        thirtyseven.setChecked(true);
        thirtyeight.setChecked(true);
        thirtynine.setChecked(true);
        forty.setChecked(true);
        fortyone.setChecked(true);
        fortytwo.setChecked(false);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void fortytwo(View view) {
        install = 42;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(true);
        thirty.setChecked(true);
        thirtyone.setChecked(true);
        thirtytwo.setChecked(true);
        thirtythree.setChecked(true);
        thirtyfour.setChecked(true);
        thirtyfive.setChecked(true);
        thirtysix.setChecked(true);
        thirtyseven.setChecked(true);
        thirtyeight.setChecked(true);
        thirtynine.setChecked(true);
        forty.setChecked(true);
        fortyone.setChecked(true);
        fortytwo.setChecked(true);
        fortythree.setChecked(false);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void fortythree(View view) {
        install = 43;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(true);
        thirty.setChecked(true);
        thirtyone.setChecked(true);
        thirtytwo.setChecked(true);
        thirtythree.setChecked(true);
        thirtyfour.setChecked(true);
        thirtyfive.setChecked(true);
        thirtysix.setChecked(true);
        thirtyseven.setChecked(true);
        thirtyeight.setChecked(true);
        thirtynine.setChecked(true);
        forty.setChecked(true);
        fortyone.setChecked(true);
        fortytwo.setChecked(true);
        fortythree.setChecked(true);
        fortyfour.setChecked(false);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void fortyfour(View view) {
        install = 44;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(true);
        thirty.setChecked(true);
        thirtyone.setChecked(true);
        thirtytwo.setChecked(true);
        thirtythree.setChecked(true);
        thirtyfour.setChecked(true);
        thirtyfive.setChecked(true);
        thirtysix.setChecked(true);
        thirtyseven.setChecked(true);
        thirtyeight.setChecked(true);
        thirtynine.setChecked(true);
        forty.setChecked(true);
        fortyone.setChecked(true);
        fortytwo.setChecked(true);
        fortythree.setChecked(true);
        fortyfour.setChecked(true);
        fortyfive.setChecked(false);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }

    public void fortyfive(View view) {
        install = 45;
        one.setChecked(true);
        two.setChecked(true);
        three.setChecked(true);
        four.setChecked(true);
        five.setChecked(true);
        six.setChecked(true);
        seven.setChecked(true);
        eight.setChecked(true);
        nine.setChecked(true);
        ten.setChecked(true);
        eleven.setChecked(true);
        twelve.setChecked(true);
        thirteen.setChecked(true);
        fourteen.setChecked(true);
        fifteen.setChecked(true);
        sixteen.setChecked(true);
        seventeen.setChecked(true);
        eighteen.setChecked(true);
        nineteen.setChecked(true);
        twenty.setChecked(true);
        twentyone.setChecked(true);
        twentytwo.setChecked(true);
        twentythree.setChecked(true);
        twentyfour.setChecked(true);
        twentyfive.setChecked(true);
        twentysix.setChecked(true);
        twentyseven.setChecked(true);
        twentyeight.setChecked(true);
        twentynine.setChecked(true);
        thirty.setChecked(true);
        thirtyone.setChecked(true);
        thirtytwo.setChecked(true);
        thirtythree.setChecked(true);
        thirtyfour.setChecked(true);
        thirtyfive.setChecked(true);
        thirtysix.setChecked(true);
        thirtyseven.setChecked(true);
        thirtyeight.setChecked(true);
        thirtynine.setChecked(true);
        forty.setChecked(true);
        fortyone.setChecked(true);
        fortytwo.setChecked(true);
        fortythree.setChecked(true);
        fortyfour.setChecked(true);
        fortyfive.setChecked(true);
        fortysix.setChecked(false);
        fortyseven.setChecked(false);
        fortyeight.setChecked(false);
    }
}


