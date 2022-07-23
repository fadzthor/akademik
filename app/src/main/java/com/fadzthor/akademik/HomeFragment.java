package com.fadzthor.akademik;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String username, namaMhs;
    private TextView tvNamamhs, tvProdi, tvSemester;
    private ImageView imageViewJadwal,imageViewProfile,imageViewKRS;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);
        tvNamamhs = view.findViewById(R.id.tvNamamhs);
        tvProdi = view.findViewById(R.id.tvProdi);
        tvSemester = view.findViewById(R.id.tvSemester);
        imageViewJadwal = view.findViewById(R.id.imageViewJadwal);
        imageViewProfile = view.findViewById(R.id.imageViewProfile);
        imageViewKRS = view.findViewById(R.id.imageViewKRS);

        if (getArguments() != null) {
            username = getArguments().getString("username");
        }
        imageViewJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("username",username);

                Intent intent = new Intent(getActivity(),JadwalActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        imageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("username",username);

                Intent intent = new Intent(getActivity(),ProfileActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        imageViewKRS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("username",username);

                Intent intent = new Intent(getActivity(),KRSActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return view;
//        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}