package smart.budget.expense.ui.options;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import smart.budget.expense.R;
import smart.budget.expense.ui.main.history.WalletEntriesRecyclerViewAdapter;

import static android.R.layout.simple_list_item_1;

public class CitiesActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<String> city = new ArrayList<String>();
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
       // city.add("Everywhere");
        lv = findViewById(R.id.listview);
        db.collection("data").document("city").get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {

                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        city = (ArrayList<String>) documentSnapshot.get("city");

                    }
                })
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(CitiesActivity.this, simple_list_item_1, city);

                        lv.setAdapter(adapter);
                    }
                });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),position,Toast.LENGTH_LONG).show();
                System.out.println(city.get(position));
                SharedPreferences sharedpreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("city", city.get(position));
                editor.commit();
                finish();

            }
        });
    }
}