package com.example.sportgym.modelos;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sportgym.listeners.PlanosListener;
import com.example.sportgym.utils.SportGymJsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class SingletonSportgym {
    private static SingletonSportgym INSTANCE = null;
    private ArrayList<Plano> planos;
    private ArrayList<Aula> aulas;
    public SportGymDBHelper sportgymDB;
    private final String mUrlAPIPlanos = "http://10.0.2.2/SportGym_PSI_2019-20/sportgym/frontend/web/v1/planos";
    private final String mUrlAPIDadosUser = "http://10.0.2.2/SportGym_PSI_2019-20/sportgym/frontend/web/v1/perfis";
    private static RequestQueue volleyQueue;
    private PlanosListener planosListener;
    private SharedPreferences user_preferences;
    private String user_token;
    private int user_id;
    private String user_username;

    public static synchronized SingletonSportgym getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SingletonSportgym(context);
        }
        volleyQueue = Volley.newRequestQueue(context);
        return INSTANCE;
    }

    public SingletonSportgym(Context context) {
        planos = new ArrayList<>();
        aulas = new ArrayList<>();
        sportgymDB = new SportGymDBHelper(context);

        user_preferences = context.getSharedPreferences("user", MODE_PRIVATE);
        user_token = user_preferences.getString("token", "ups");
        user_id = user_preferences.getInt("id", -1);
    }

    public void setPlanosListener(PlanosListener planosListener) {
        this.planosListener = planosListener;
    }

    public String getUsername() {
        return user_username;
    }

    public Plano getPlano(int idPlano) {
        for (Plano I : planos) {
            if (I.getId() == idPlano) {
                return I;
            }
        }
        return null;
    }

    public void adicionarPlanoBD(Plano plano) {
        sportgymDB.adicionarPlanosBD(plano);

    }

    public void adicionarPlanosBD(ArrayList<Plano> listaPlanos) {
        sportgymDB.removerAllPlanosBD();
        for (Plano plano : listaPlanos) {
            adicionarPlanoBD(plano);
        }
    }

    public ArrayList<Aula> getAulasBD() {
        aulas = sportgymDB.getAulas();
        return aulas;
    }

    /********************* Acessos API ******************/

    public void getUsername(final Context context, boolean isConnected) {
        if (!isConnected) {


        } else {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, mUrlAPIDadosUser + "/" + user_id + "/username?access-token=" + user_token, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                user_username = response.getString("username");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                        }
                    });
            volleyQueue.add(request);
        }
    }

    public void putAlterarUsernameAPI(final Context context, String username) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
        } catch (JSONException e) {
            // handle exception
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, mUrlAPIDadosUser + "/" + user_id + "/alterar-username?access-token=" + user_token, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context, "Username alterado com sucesso", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Username Já Existe", Toast.LENGTH_SHORT).show();
                    }
                });
        volleyQueue.add(request);
    }

    public void putAlterarPasswordAPI(final Context context, String password, String password_atual) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("password", password);
            jsonObject.put("password_atual", password_atual);
        } catch (JSONException e) {
            // handle exception
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, mUrlAPIDadosUser + "/" + user_id + "/alterar-password?access-token=" + user_token, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context, "Password alterada com sucesso", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Password atual errada", Toast.LENGTH_SHORT).show();
                    }
                });
        volleyQueue.add(request);
    }

    public void putAlterarPesoAlturaAPI(final Context context, float peso, int altura) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("peso", peso);
            jsonObject.put("altura", altura);
        } catch (JSONException e) {
            // handle exception
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, mUrlAPIDadosUser + "/" + user_id + "/alterar-peso-altura?access-token=" + user_token, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        volleyQueue.add(request);
    }

    public void getAllPlanosNutricaoAPI(final Context context, boolean isConnected) {
        if (!isConnected) {
            planos = sportgymDB.getPlanos();
            Toast.makeText(context, "Sem Conexão à internet", Toast.LENGTH_SHORT).show();

            if (planosListener != null) {
                planosListener.onRefreshPlanos(planos);
            }

        } else {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, mUrlAPIPlanos + "/" + user_id + "/planos-nutricao?access-token=" + user_token, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            planos = SportGymJsonParser.parserJsonPlanosNutricao(response, context);
                            adicionarPlanosBD(planos);
                            if (planos == null) {
                                Toast.makeText(context, "sem planos", Toast.LENGTH_SHORT).show();
                            }

                            // DONE: informar a vista
                            if (planos != null) {
                                planosListener.onRefreshPlanos(planos);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                        }
                    });
            volleyQueue.add(request);
        }
    }

    public void getAllPlanosTreinoAPI(final Context context, boolean isConnected) {
        if (!isConnected) {
            planos = sportgymDB.getPlanos();
            Toast.makeText(context, "Sem Conexão à internet", Toast.LENGTH_SHORT).show();

            if (planosListener != null) {
                planosListener.onRefreshPlanos(planos);
            }

        } else {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, mUrlAPIPlanos + "/" + user_id + "/planos-treino?access-token=" + user_token, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            planos = SportGymJsonParser.parserJsonPlanosTreino(response, context);
                            adicionarPlanosBD(planos);
                            if (planos == null) {
                                Toast.makeText(context, "sem planos", Toast.LENGTH_SHORT).show();
                            }

                            // DONE: informar a vista
                            if (planos != null) {
                                planosListener.onRefreshPlanos(planos);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                        }
                    });
            volleyQueue.add(request);
        }
    }

    public void adicionarPlanoTreinoAPI(final Context context, boolean isConnected, final Plano plano) {
        if (!isConnected) {
            //----------------------->>>>>>>>>>>>>>>>>>>>
            adicionarPlanoBD(plano);
            Toast.makeText(context, "Sem Conexão à Internet", Toast.LENGTH_SHORT).show();

            if (planosListener != null) {
                planosListener.onRefreshPlanos(planos);
            }
        } else {
            final JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("nome", plano.getNome());
                jsonObject.put("descricao", plano.getDescricao());
            } catch (JSONException e) {
                // handle exception
            }


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, mUrlAPIPlanos + "/" + user_id + "/novo-plano-treino?access-token=" + user_token, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            if (planos != null) {
                                planosListener.onRefreshPlanos(planos);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                        }
                    });
            volleyQueue.add(request);
        }
    }

    public void adicionarPlanoNutricaoAPI(final Context context, boolean isConnected, final Plano plano) {
        if (!isConnected) {
            //----------------------->>>>>>>>>>>>>>>>>>>>
            adicionarPlanoBD(plano);
            Toast.makeText(context, "Sem Conexão à Internet", Toast.LENGTH_SHORT).show();

            if (planosListener != null) {
                planosListener.onRefreshPlanos(planos);
            }
        } else {
            final JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("nome", plano.getNome());
                jsonObject.put("descricao", plano.getDescricao());
            } catch (JSONException e) {
                // handle exception
            }


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, mUrlAPIPlanos + "/" + user_id + "/novo-plano-nutricao?access-token=" + user_token, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            if (planos != null) {
                                planosListener.onRefreshPlanos(planos);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                        }
                    });
            volleyQueue.add(request);
        }
    }

}


