package com.example.isocr.cvas.Fragments;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isocr.cvas.R;

import java.util.HashMap;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment implements TextToSpeech.OnInitListener {


    public ThirdFragment() {
        // Required empty public constructor
    }

    public TextToSpeech tts;
    public boolean ttsInit ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        getActivity().setTitle("Palabras Predeterminadas");

        ListView lista = (ListView)view.findViewById(R.id.lista);

        final String[] itemname ={
                " ",
                " ",
                " ",
                " ",
                " ",
                " ",
                " ",
                " ",
        };

        Integer[] imgid={
                R.drawable.ic_volume_up_black_24dp,
                R.drawable.ic_volume_up_black_24dp,
                R.drawable.ic_volume_up_black_24dp,
                R.drawable.ic_volume_up_black_24dp,
                R.drawable.ic_volume_up_black_24dp,
                R.drawable.ic_volume_up_black_24dp,
                R.drawable.ic_volume_up_black_24dp,
                R.drawable.ic_volume_up_black_24dp,
        };

        CustomListAdapter adapter=new CustomListAdapter(getActivity(), itemname, imgid);


        lista.setAdapter(adapter);

        //TEXT TO SPEECH
        tts = new TextToSpeech(getContext(), this);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Hablar(itemname[position]);
            }
        });

        return view;
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS){
            Locale locale = new Locale("es", "ES");
            int result = tts.setLanguage(locale);

            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(getContext(), "Language not supported", Toast.LENGTH_SHORT).show();
            }
            else{
                ttsInit = true;
            }
        }
        else{
            Toast.makeText(getContext(), "Failed to initialize!", Toast.LENGTH_SHORT).show();
        }
    }

    public void Hablar(String txt){
        if(!ttsInit){
            return;
        }
        else{
            HashMap<String, String> map = new HashMap<>();
            map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
            tts.speak(txt, TextToSpeech.QUEUE_FLUSH, map);
        }
    }

    public class CustomListAdapter extends ArrayAdapter<String> {

        private final Activity context;
        private final String[] itemname;
        private final Integer[] imgid;

        public CustomListAdapter(Activity context, String[] itemname, Integer[] imgid) {
            super(context, R.layout.mylist, itemname);
            // TODO Auto-generated constructor stub

            this.context=context;
            this.itemname=itemname;
            this.imgid=imgid;
        }

        public View getView(int position,View view,ViewGroup parent) {
            LayoutInflater inflater=context.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.mylist, null,true);

            TextView txtTitle = (TextView) rowView.findViewById(R.id.Itemname);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

            txtTitle.setText(itemname[position]);
            imageView.setImageResource(imgid[position]);

            return rowView;

        };
    }

}
