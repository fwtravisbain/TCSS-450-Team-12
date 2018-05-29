package edu.tacoma.uw.css.sextod.memeups;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import edu.tacoma.uw.css.sextod.memeups.database.Match;


/**
 *
 */
public class ProfileViewFragment extends Fragment {


    public final static String COURSE_ITEM_SELECTED = "course_selected";

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Match mCurrentUser;

    //    private TextView mCourseIdTextView;
//    private TextView mCourseShortDescTextView;
    private TextView mCourseLongDescTextView;
    private TextView mCoursePrereqsTextView;

    private Button matchbutton;
    private MatchListener mListener;


    /**
     * Listener for the registration button
     */
    public interface MatchListener {
        //public void register(String url);
        public void matchRequest(String email);
    }

    //private OnFragmentInteractionListener mListener;

    public ProfileViewFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment ProfileViewFragment.
//     */
//    public static ProfileViewFragment newInstance(String param1, String param2) {
//        ProfileViewFragment fragment = new ProfileViewFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_view_profile, container, false);
//        mCourseIdTextView = (TextView) view.findViewById(R.id.course_item_id);
//        mCourseShortDescTextView = (TextView) view.findViewById(R.id.course_short_desc);
        mCourseLongDescTextView = (TextView) view.findViewById(R.id.course_long_desc);
        mCoursePrereqsTextView = (TextView) view.findViewById(R.id.course_prereqs);

        //match button
        Button registerButton = (Button) view.findViewById(R.id.match_button);
        /**
         * Listener for match button
         */
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do matching stuff
                mListener.matchRequest(mCurrentUser.getmEmail());
            }
        });


        return view;

    }

    public void updateView(Match course) {
        if (course != null) {
//            mCourseIdTextView.setText(course.getCourseId());
//            mCourseShortDescTextView.setText(course.getShortDescription());
            mCourseLongDescTextView.setText(course.getmFirst());
            mCoursePrereqsTextView.setText(course.getmBio());
        }
    }



    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }

        // do stuff
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MatchListener) {
            mListener = (MatchListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle args = getArguments();
        if (args != null) {
            // Set course information based on argument passed
            updateView((Match) args.getSerializable(COURSE_ITEM_SELECTED));
            mCurrentUser = (Match) args.getSerializable(COURSE_ITEM_SELECTED);
        } else {
            getActivity().getSupportFragmentManager().popBackStack();
        }

    }


}