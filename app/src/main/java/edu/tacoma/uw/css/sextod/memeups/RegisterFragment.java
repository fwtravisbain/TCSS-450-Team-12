/**
 * Register fragment opens the page for the user to create an account using an email address and password.
 * The email address used must be unique and valid; the password must be at least 5 characters long.
 * These fields are put into a url string that is used to pass the email and password into the php
 * file hosted on the server to add this new user into the database.
 *
 * @author Travis Bain
 * @author Kerry Fergurson
 * @author Dirk Sexton
 * @version 1.0
 * @since 1.0
 */

package edu.tacoma.uw.css.sextod.memeups;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URLEncoder;

import edu.tacoma.uw.css.sextod.memeups.database.User;

import static android.content.ContentValues.TAG;

/**
 * This fragment is called from the LoginActivity when a user does not already have
 * an account with us. They will be able to register their email, password, first and
 * last name, and a username. Our webservice will remember their login credentials
 * so they can login with it.
 *
 * @author Kerry Ferguson
 * @author Travis Bain
 * @author Dirk Sexton
 */
public class RegisterFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";
    private final static String COURSE_ADD_URL
            = "http://kferg9.000webhostapp.com/android/addUser.php?";

    private SharedPreferences mSharedPreferences;


    private RegisterListener mListener;

    private Button registerbutton;

    private EditText mEmail;
    private EditText mPassword;
    private EditText mFirst;
    private EditText mLast;
    private EditText mUsername;

    /**
     * Listener for the registration button
     */
    public interface RegisterListener {
        public void register(String url, String email);
    }

    /**
     * Empty constructor
     */
    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RegisterFragment.
     */
    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Initialize the variables on create
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Initializes the variables to the values of the text fields and listens for the register button
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        //Set email and password
        mEmail = (EditText) v.findViewById(R.id.loginEmail);
        mPassword = (EditText) v.findViewById(R.id.registerPassword);
        mFirst = (EditText) v.findViewById(R.id.registerFirst);
        mLast = (EditText) v.findViewById(R.id.registerLast);
        mUsername = (EditText) v.findViewById(R.id.registerUsername);

        mSharedPreferences = getActivity().getSharedPreferences(getString(R.string.LOGIN_PREFS)
                , Context.MODE_PRIVATE);

        //Button for registration
        Button button = (Button)
                getActivity().findViewById(R.id.newuserbutton);
        Button registerButton = (Button) v.findViewById(R.id.registerbutton);
        /**
         * Listener for registration button
         */
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Build url using email and password and then call register function with the url
                String url = buildCourseURL(v);
                Log.i(TAG, "Writing email to storage:");
                Log.i(TAG, mEmail.getText().toString());

//                mSharedPreferences
//                        .edit()
//                        .putBoolean(getString(R.string.LOGGEDIN), true)
//                        .putString("email", mEmail.getText().toString())
//                        .commit();

                try {
                    //Create user object to validate fields
                    User user = new User(mEmail.getText().toString(), mPassword.getText().toString());

                    mListener.register(url, mEmail.getText().toString());
                } catch (Exception e){
                    Toast.makeText(v.getContext(),
                            "Unable to register: " + e.getMessage()
                            , Toast.LENGTH_SHORT)
                            .show();
                }


            }
        });

        return v;
    }

    /**
     * On attach function with listener
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RegisterListener) {
            mListener = (RegisterListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement RegisterListener");
        }
    }

    /**
     * Detach sets listener to null
     */
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

    /**
     * Builds a url to be passed into php registeration function using email and password
     * @param v view
     * @return The url in a string format
     */
    private String buildCourseURL(View v) {

        StringBuilder sb = new StringBuilder(COURSE_ADD_URL);

        try {
            String email = mEmail.getText().toString();
            sb.append("email=");
            sb.append(URLEncoder.encode(email, "UTF-8"));
            String password = mPassword.getText().toString();
            sb.append("&password=");
            sb.append(URLEncoder.encode(password, "UTF-8"));
            String first = mFirst.getText().toString();
            sb.append("&first=");
            sb.append(URLEncoder.encode(first, "UTF-8"));
            String last = mLast.getText().toString();
            sb.append("&last=");
            sb.append(URLEncoder.encode(last, "UTF-8"));
            String username = mUsername.getText().toString();
            sb.append("&username=");
            sb.append(URLEncoder.encode(username, "UTF-8"));


            //Log.i(TAG sb.toString());
            Log.i(TAG, sb.toString());

        }
        catch(Exception e) {
            Toast.makeText(v.getContext(), "Something wrong with the url" + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
        return sb.toString();
    }



}
