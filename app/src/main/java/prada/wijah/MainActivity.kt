package prada.wijah

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import prada.wijah.ui.theme.WijahTheme
import android.widget.RadioGroup
import android.content.Intent
import android.net.Uri
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.view.inputmethod.EditorInfo
import android.view.View


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WijahTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var currentScreen by remember { mutableStateOf("register") }

                    val screenMap: Map<String, @Composable (Modifier) -> Unit> = mapOf(
                        "register" to { modifier ->
                            ShowRegisterLayout(
                                modifier = modifier,
                                onRegisterClick = { currentScreen = "login" }
                            )
                        },
                        "profile" to { modifier ->
                            ShowProfileLayout(
                                modifier = modifier,
                                onBackClick = { currentScreen = "hal_jatim" },
                                onKeluarClick = { currentScreen = "register" }
                            )
                        },
                        "login" to { modifier ->
                            ShowLoginLayout(
                                modifier = modifier,
                                onLoginClick = { currentScreen = "hal_1" }
                            )
                        },
                        "hal_1" to { modifier ->
                            ShowHal1Layout(
                                modifier = modifier,
                                onStartClick = { destination ->
                                    currentScreen = when (destination) {
                                        "Jawa Timur" -> "hal_jatim"
                                        "Jawa Tengah" -> "hal_jateng"
                                        "Jawa Barat" -> "hal_jabar"
                                        else -> "hal_1"
                                    }
                                }
                            )
                        },
                        "hal_jatim" to { modifier ->
                            ShowHaljatimLayout(
                                modifier = modifier,
                                onLocationClick = { currentScreen = "hal_3" },
                                onBackClick = { currentScreen = "hal_1" },
                                onProfileClick = { currentScreen = "profile" }
                            )
                        },
                        "hal_3" to { modifier ->
                            ShowHal3Layout(
                                modifier = modifier,
                                onSingosariClick = {
                                    currentScreen = "hal_4" // Ketika tombol_singosari diklik, arahkan ke hal_4
                                },
                                onBackClick = {
                                    currentScreen = "hal_jatim" // Ketika tombol kembali diklik, arahkan ke hal_jatim
                                },
                                onPanataranClick = {
                                    currentScreen = "hal_5" // Ketika tombol_panataran diklik, arahkan ke hal_5
                                },
                                onKidalClick = {
                                    currentScreen = "hal_6" // Ketika tombol_kidal diklik, arahkan ke hal_6
                                },
                                onMuseumClick = {
                                    currentScreen = "hal_7" // Ketika tombol_museum diklik, arahkan ke hal_museum
                                },
                                onMakamClick = {
                                    currentScreen = "hal_8" // Ketika tombol_makam diklik, arahkan ke hal_makam
                                },
                                onMajapahitClick = {
                                    currentScreen = "hal_9" // Ketika tombol_majapahit diklik, arahkan ke hal_majapahit
                                }
                            )
                        },
                        "hal_4" to { modifier ->
                            ShowHal4Layout(
                                modifier = modifier,
                                onLocationClick = { currentScreen = "hal_5" },
                                onBackClick = { currentScreen = "hal_3" }
                            )
                        },
                        "hal_5" to { modifier ->
                            ShowHal5Layout(
                                modifier = modifier,
                                onLocationClick = { currentScreen = "hal_5" },
                                onBackClick = { currentScreen = "hal_3" }
                            )
                        },
                        "hal_6" to { modifier ->
                            ShowHal6Layout(
                                modifier = modifier,
                                onLocationClick = { currentScreen = "hal_6" },
                                onBackClick = { currentScreen = "hal_3" }
                            )
                        },
                        "hal_7" to { modifier ->
                            ShowHal7Layout(
                                modifier = modifier,
                                onLocationClick = { currentScreen = "hal_7" },
                                onBackClick = { currentScreen = "hal_3" }
                            )
                        },
                        "hal_8" to { modifier ->
                            ShowHal8Layout(
                                modifier = modifier,
                                onLocationClick = { currentScreen = "hal_8" },
                                onBackClick = { currentScreen = "hal_3" }
                            )
                        },
                        "hal_9" to { modifier ->
                            ShowHal9Layout(
                                modifier = modifier,
                                onLocationClick = { currentScreen = "hal_9" },
                                onBackClick = { currentScreen = "hal_3" }
                            )
                        },
                        "hal_jabar" to { modifier ->
                            ShowHaljabarLayout(
                                modifier = modifier,
                                onLocationClick = { currentScreen = "hal_jabarat" },
                                onBackClick = { currentScreen = "hal_1" },
                                onProfileClick = { currentScreen = "profile" }
                            )
                        },
                        "hal_jabarat" to { modifier ->
                            ShowHalJabaratLayout(
                                modifier = modifier,
                                onGedungSateClick = { currentScreen = "hal_16" },   // Gedung Sate
                                onKonferensiClick = { currentScreen = "hal_17" },  // Konferensi
                                onMonumenClick = { currentScreen = "hal_18" },      // Monumen
                                onSaungClick = { currentScreen = "hal_19" },        // Saung
                                onGeologiClick = { currentScreen = "hal_20" },      // Geologi
                                onKesepuhanClick = { currentScreen = "hal_21" },    // Kesepuhan
                                onBackClick = { currentScreen = "hal_jabar" }     // Tombol kembali
                            )
                        },
                        "hal_16" to { modifier ->
                            ShowHal16Layout(
                                modifier = modifier,
                                onLocationClick = { /* Tambahkan aksi jika diperlukan */ },
                                onBackClick = { currentScreen = "hal_jabarat" } // Navigasi kembali ke `hal_jabarat`
                            )
                        },
                        "hal_17" to { modifier ->
                            ShowHal17Layout(
                                modifier = modifier,
                                onLocationClick = { /* Aksi untuk lokasi pada hal_17 */ },
                                onBackClick = { currentScreen = "hal_jabarat" } // Kembali ke `hal_jabarat`
                            )
                        },
                        "hal_18" to { modifier ->
                            ShowHal18Layout(
                                modifier = modifier,
                                onLocationClick = { /* Aksi untuk lokasi pada hal_18 */ },
                                onBackClick = { currentScreen = "hal_jabarat" } // Kembali ke `hal_jabarat`
                            )
                        },
                        "hal_19" to { modifier ->
                            ShowHal19Layout(
                                modifier = modifier,
                                onLocationClick = { /* Aksi untuk lokasi pada hal_19 */ },
                                onBackClick = { currentScreen = "hal_jabarat" } // Kembali ke `hal_jabarat`
                            )
                        },
                        "hal_20" to { modifier ->
                            ShowHal20Layout(
                                modifier = modifier,
                                onLocationClick = { /* Aksi untuk lokasi pada hal_20 */ },
                                onBackClick = { currentScreen = "hal_jabarat" } // Kembali ke `hal_jabarat`
                            )
                        },
                        "hal_21" to { modifier ->
                            ShowHal21Layout(
                                modifier = modifier,
                                onLocationClick = { /* Aksi untuk lokasi pada hal_21 */ },
                                onBackClick = { currentScreen = "hal_jabarat" } // Kembali ke `hal_jabarat`
                            )
                        },
                        "hal_jateng" to { modifier ->
                            ShowHaljatengLayout(
                                modifier = modifier,
                                onLocationClick = { currentScreen = "hal_jatengah" },
                                onBackClick = { currentScreen = "hal_1" },
                                onProfileClick = { currentScreen = "profile" },
                                onLawangClick = { currentScreen = "hal_10" },
                                onKotaClick = { currentScreen = "hal_11" }, // Tombol btn_kota juga mengarah ke hal_jatengah
                                onSampokongClick = { currentScreen = "hal_12" }, // Navigasi ke sampokong
                                onAgungClick = { currentScreen = "hal_13" }, // Navigasi ke agung
                                onGedungClick = { currentScreen = "hal_14" }, // Navigasi ke gedung
                                onKeratonClick = { currentScreen = "hal_15" }, // Navigasi ke gedung
                                onKampungClick = { currentScreen = "hal_jatengah" }, // Navigasi ke kampung
                                onPustakaClick = { currentScreen = "hal_jatengah" }, // Navigasi ke pustaka
                                onBentengClick = { currentScreen = "hal_jatengah" } // Navigasi ke benteng
                            )
                        },
                        "hal_jatengah" to { modifier ->
                            ShowHalJatengahLayout(
                                modifier = modifier,
                                onFirstImageClick = { currentScreen = "hal_10" },   // Lawang Sewu
                                onSecondImageClick = { currentScreen = "hal_11" },  // Kota
                                onSempokongClick = { currentScreen = "hal_12" },    // Sempokong
                                onMasjidClick = { currentScreen = "hal_13" },       // Masjid
                                onMarabuntaClick = { currentScreen = "hal_14" },    // Marabunta
                                onKeratonClick = { currentScreen = "hal_15" },      // Keraton
                                onBackClick = { currentScreen = "hal_jateng" }           // Tombol kembali
                            )
                        },
                        "hal_10" to { modifier ->
                            ShowHal10Layout(
                                modifier = modifier,
                                onLocationClick = { /* Tambahkan aksi jika diperlukan */ },
                                onBackClick = { currentScreen = "hal_jatengah" } // Navigasi kembali ke `hal_jatengah`
                            )
                        },
                        "hal_11" to { modifier ->
                            ShowHal11Layout(
                                modifier = modifier,
                                onLocationClick = { /* Aksi untuk lokasi pada hal_11 */ },
                                onBackClick = { currentScreen = "hal_jatengah" } // Kembali ke hal_10
                            )
                        },
                        "hal_12" to { modifier ->
                            ShowHal12Layout(
                                modifier = modifier,
                                onLocationClick = { /* Aksi untuk lokasi pada hal_12 */ },
                                onBackClick = { currentScreen = "hal_jatengah" } // Kembali ke hal_11
                            )
                        },
                        "hal_13" to { modifier ->
                            ShowHal13Layout(
                                modifier = modifier,
                                onLocationClick = { /* Aksi untuk lokasi pada hal_13 */ },
                                onBackClick = { currentScreen = "hal_jatengah" } // Kembali ke hal_12
                            )
                        },
                        "hal_14" to { modifier ->
                            ShowHal14Layout(
                                modifier = modifier,
                                onLocationClick = { /* Aksi untuk lokasi pada hal_14 */ },
                                onBackClick = { currentScreen = "hal_jatengah" } // Kembali ke hal_13
                            )
                        },
                        "hal_15" to { modifier ->
                            ShowHal15Layout(
                                modifier = modifier,
                                onLocationClick = { /* Aksi untuk lokasi pada hal_15 */ },
                                onBackClick = { currentScreen = "hal_jatengah" } // Kembali ke hal_14
                            )
                        },
                        )

                    screenMap[currentScreen]?.invoke(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ShowHal3Layout(
    modifier: Modifier = Modifier,
    onSingosariClick: () -> Unit,
    onBackClick: () -> Unit,
    onPanataranClick: () -> Unit,
    onKidalClick: () -> Unit,
    onMuseumClick: () -> Unit,
    onMakamClick: () -> Unit,
    onMajapahitClick: () -> Unit
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_3, null).apply {
                val searchBar: AutoCompleteTextView = findViewById(R.id.search_bar)

                // Mapping lokasi berdasarkan ID dan teks
                val locationButtons = mapOf(
                    "candi singosari" to findViewById<TextView>(R.id.btn_singosari),
                    "candi panataran" to findViewById<TextView>(R.id.btn_panataran),
                    "candi kidal" to findViewById<TextView>(R.id.btn_kidal),
                    "museum sepuluh n" to findViewById<TextView>(R.id.btn_museum),
                    "makan bung karno" to findViewById<TextView>(R.id.btn_makam),
                    "museum majapahit" to findViewById<TextView>(R.id.btn_majapahit)
                )

                val places = locationButtons.keys.toList()

                // Sembunyikan semua tombol lokasi
                fun resetVisibility() {
                    locationButtons.values.forEach { it.visibility = View.GONE }
                }

                // Adapter untuk AutoCompleteTextView
                val adapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, places)
                searchBar.setAdapter(adapter)

                // Aksi ketika item dipilih dari saran
                searchBar.setOnItemClickListener { _, _, position, _ ->
                    val query = adapter.getItem(position)?.lowercase()
                    resetVisibility() // Sembunyikan semua lokasi
                    query?.let {
                        locationButtons[it]?.visibility = View.VISIBLE
                    }
                }

                // Aksi ketika tombol Enter ditekan
                searchBar.setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                        val query = searchBar.text.toString().trim().lowercase()
                        resetVisibility() // Sembunyikan semua lokasi
                        if (locationButtons.containsKey(query)) {
                            locationButtons[query]?.visibility = View.VISIBLE
                        } else {
                            Toast.makeText(context, "Lokasi tidak ditemukan", Toast.LENGTH_SHORT).show()
                        }
                        true
                    } else {
                        false
                    }
                }

                // Aksi tombol lokasi
                locationButtons["candi singosari"]?.setOnClickListener { onSingosariClick() }
                locationButtons["candi panataran"]?.setOnClickListener { onPanataranClick() }
                locationButtons["candi kidal"]?.setOnClickListener { onKidalClick() }
                locationButtons["museum sepuluh n"]?.setOnClickListener { onMuseumClick() }
                locationButtons["makan bung karno"]?.setOnClickListener { onMakamClick() }
                locationButtons["museum majapahit"]?.setOnClickListener { onMajapahitClick() }

                // Tombol kembali
                findViewById<TextView>(R.id.back_button).setOnClickListener { onBackClick() }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowRegisterLayout(modifier: Modifier = Modifier, onRegisterClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(false) }

    // Fungsi untuk memeriksa validasi input
    fun isInputValid(): Boolean {
        return email.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    LaunchedEffect(email, username, password) {
        isButtonEnabled = isInputValid()
    }

    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.register, null)
            val emailField: EditText = view.findViewById(R.id.et_email)
            val usernameField: EditText = view.findViewById(R.id.et_username)
            val passwordField: EditText = view.findViewById(R.id.et_password)
            val registerButton: Button = view.findViewById(R.id.btn_register)
            val loginTextView: TextView = view.findViewById(R.id.tv_login)

            emailField.setText(email)
            usernameField.setText(username)
            passwordField.setText(password)

            emailField.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    email = s.toString()
                }
                override fun afterTextChanged(s: Editable?) {}
            })

            usernameField.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    username = s.toString()
                }
                override fun afterTextChanged(s: Editable?) {}
            })

            passwordField.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    password = s.toString()
                }
                override fun afterTextChanged(s: Editable?) {}
            })

            // Tombol Register klik handler
            registerButton.setOnClickListener {
                if (isInputValid()) {
                    // Simpan data ke SharedPreferences
                    val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_profile", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("email", email)
                    editor.putString("username", username)
                    editor.putString("password", password)
                    editor.apply()

                    // Memberikan feedback kepada pengguna
                    Toast.makeText(context, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show()

                    // Panggil onRegisterClick untuk berpindah ke halaman login
                    onRegisterClick()
                } else {
                    Toast.makeText(context, "Pastikan semua input valid!", Toast.LENGTH_SHORT).show()
                }
            }

            // Tombol Login klik handler
            loginTextView.setOnClickListener {
                onRegisterClick()  // Mengarahkan ke halaman login
            }

            view
        },
        modifier = modifier.fillMaxSize()
    )
}


@Composable
fun ShowProfileLayout(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onKeluarClick: () -> Unit
) {
    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.profil, null)
            val backButton: TextView = view.findViewById(R.id.back_button)
            val keluarButton: Button = view.findViewById(R.id.btn_keluar)

            // Menangani klik tombol Kembali
            backButton.setOnClickListener {
                onBackClick() // Arahkan kembali ke hal_jatim
            }

            // Menangani klik tombol Keluar
            keluarButton.setOnClickListener {
                onKeluarClick() // Pindah ke halaman register
            }

            view
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowLoginLayout(modifier: Modifier = Modifier, onLoginClick: () -> Unit) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.login, null).apply {
                findViewById<Button>(R.id.btn_login).setOnClickListener {
                    val username = findViewById<EditText>(R.id.username).text.toString()
                    val password = findViewById<EditText>(R.id.password).text.toString()

                    val sharedPrefs = context.getSharedPreferences("user_profile", Context.MODE_PRIVATE)
                    if (username == sharedPrefs.getString("username", "") && password == sharedPrefs.getString("password", "")) {
                        onLoginClick()
                        Toast.makeText(context, "Login Berhasil!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Username atau Password Salah", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal1Layout(modifier: Modifier = Modifier, onStartClick: (String) -> Unit) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_1, null).apply {
                val radioGroup = findViewById<RadioGroup>(R.id.radio_group)
                val startButton = findViewById<Button>(R.id.btn_start)

                // Enable start button only if a radio button is selected
                radioGroup.setOnCheckedChangeListener { _, _ ->
                    startButton.isEnabled = radioGroup.checkedRadioButtonId != -1
                }

                startButton.setOnClickListener {
                    val selectedRadioButton = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
                    val destination = selectedRadioButton.text.toString()
                    onStartClick(destination) // Navigate based on selected destination
                }

                startButton.isEnabled = radioGroup.checkedRadioButtonId != -1
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHaljatimLayout(
    modifier: Modifier = Modifier,
    onLocationClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_jatim, null).apply {
                val searchBar: AutoCompleteTextView = findViewById(R.id.search_bar)

                val locationButtons = mapOf(
                    "singosari" to findViewById<TextView>(R.id.btn_singosari),
                    "panataran" to findViewById<TextView>(R.id.btn_panataran),
                    "kidal" to findViewById<TextView>(R.id.btn_kidal),
                    "museum" to findViewById<TextView>(R.id.btn_museum),
                    "makam" to findViewById<TextView>(R.id.btn_makam),
                    "majapahit" to findViewById<TextView>(R.id.btn_majapahit)
                )

                val places = locationButtons.keys.toList()

                // Menyembunyikan semua lokasi terlebih dahulu
                fun resetVisibility() {
                    locationButtons.values.forEach { it.visibility = View.GONE }
                }

                // Membuat ArrayAdapter untuk AutoCompleteTextView
                val adapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, places)
                searchBar.setAdapter(adapter)

                // Aksi ketika tombol Enter ditekan
                searchBar.setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                        val query = searchBar.text.toString().trim().lowercase()
                        resetVisibility() // Sembunyikan semua lokasi

                        if (locationButtons.containsKey(query)) {
                            locationButtons[query]?.visibility = View.VISIBLE
                        } else {
                            Toast.makeText(context, "Lokasi tidak ditemukan", Toast.LENGTH_SHORT).show()
                        }
                        true
                    } else {
                        false
                    }
                }

                // Menambahkan aksi klik lokasi
                findViewById<ImageButton>(R.id.btn_location1).setOnClickListener { onLocationClick(1) }
                findViewById<ImageButton>(R.id.btn_location2).setOnClickListener { onLocationClick(2) }
                findViewById<ImageButton>(R.id.btn_location3).setOnClickListener { onLocationClick(3) }

                locationButtons.forEach { (key, button) ->
                    button.setOnClickListener { onLocationClick(places.indexOf(key) + 4) }
                }

                // Back dan Profile button
                findViewById<TextView>(R.id.back_button).setOnClickListener { onBackClick() }
                findViewById<ImageButton>(R.id.btn_profile).setOnClickListener { onProfileClick() }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}


@Composable
fun ShowHal4Layout(
    modifier: Modifier = Modifier,
    onLocationClick: () -> Unit, // Tambahkan parameter ini
    onBackClick: () -> Unit
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_4, null).apply {
                findViewById<TextView>(R.id.back_button).setOnClickListener { onBackClick() }
                findViewById<Button>(R.id.btn_lihat_lokasi).setOnClickListener {
                    val locationUrl = "https://maps.app.goo.gl/KaAd2oyjSNqz65N88"
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl)))
                    onLocationClick() // Panggil onLocationClick setelah URL dibuka
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal5Layout(
    modifier: Modifier = Modifier,
    onLocationClick: () -> Unit, // Callback untuk lokasi
    onBackClick: () -> Unit // Callback untuk tombol kembali
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_5, null).apply {
                // Menangani klik pada tombol kembali
                findViewById<TextView>(R.id.back_button).setOnClickListener {
                    onBackClick() // Panggil callback untuk kembali
                }

                // Menangani klik pada tombol lihat lokasi
                findViewById<Button>(R.id.btn_lihat_lokasi).setOnClickListener {
                    val locationUrl = "https://maps.app.goo.gl/185g737L3a6UfWjn6"
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl))) // Membuka URL
                    onLocationClick() // Panggil callback setelah URL dibuka
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal6Layout(
    modifier: Modifier = Modifier,
    onLocationClick: () -> Unit, // Callback untuk lokasi
    onBackClick: () -> Unit // Callback untuk tombol kembali
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_6, null).apply {
                // Menangani klik pada tombol kembali
                findViewById<TextView>(R.id.back_button).setOnClickListener {
                    onBackClick() // Panggil callback untuk kembali
                }

                // Menangani klik pada tombol lihat lokasi
                findViewById<Button>(R.id.btn_lihat_lokasi).setOnClickListener {
                    val locationUrl = "https://maps.app.goo.gl/1MRrsQdvJNSCRwb27"
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl))) // Membuka URL
                    onLocationClick() // Panggil callback setelah URL dibuka
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}


@Composable
fun ShowHal7Layout(
    modifier: Modifier = Modifier,
    onLocationClick: () -> Unit, // Callback untuk lokasi
    onBackClick: () -> Unit // Callback untuk tombol kembali
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_7, null).apply {
                // Menangani klik pada tombol kembali
                findViewById<TextView>(R.id.back_button).setOnClickListener {
                    onBackClick() // Panggil callback untuk kembali
                }

                // Menangani klik pada tombol lihat lokasi
                findViewById<Button>(R.id.btn_lihat_lokasi).setOnClickListener {
                    val locationUrl = "https://maps.app.goo.gl/ktCXEKKoKKSj6R9w7"
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl))) // Membuka URL
                    onLocationClick() // Panggil callback setelah URL dibuka
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal8Layout(
    modifier: Modifier = Modifier,
    onLocationClick: () -> Unit, // Callback untuk lokasi
    onBackClick: () -> Unit // Callback untuk tombol kembali
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_8, null).apply {
                // Menangani klik pada tombol kembali
                findViewById<TextView>(R.id.back_button).setOnClickListener {
                    onBackClick() // Panggil callback untuk kembali
                }

                // Menangani klik pada tombol lihat lokasi
                findViewById<Button>(R.id.btn_lihat_lokasi).setOnClickListener {
                    val locationUrl = "https://maps.app.goo.gl/cm9Z4Majx2m1GZCHA"
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl))) // Membuka URL
                    onLocationClick() // Panggil callback setelah URL dibuka
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal9Layout(
    modifier: Modifier = Modifier,
    onLocationClick: () -> Unit, // Callback untuk lokasi
    onBackClick: () -> Unit // Callback untuk tombol kembali
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_9, null).apply {
                // Menangani klik pada tombol kembali
                findViewById<TextView>(R.id.back_button).setOnClickListener {
                    onBackClick() // Panggil callback untuk kembali
                }

                // Menangani klik pada tombol lihat lokasi
                findViewById<Button>(R.id.btn_lihat_lokasi).setOnClickListener {
                    val locationUrl = "https://maps.app.goo.gl/duN6JMwehqcHz4vD9"
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl))) // Membuka URL
                    onLocationClick() // Panggil callback setelah URL dibuka
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHaljatengLayout(
    modifier: Modifier = Modifier,
    onLocationClick: (Int) -> Unit, // Parameter untuk lokasi spesifik
    onBackClick: () -> Unit,
    onProfileClick: () -> Unit,
    onLawangClick: () -> Unit, // Klik untuk btn_lawang
    onKotaClick: () -> Unit, // Klik untuk btn_kota
    onSampokongClick: () -> Unit, // Klik untuk btn_sampokong
    onAgungClick: () -> Unit, // Klik untuk btn_agung
    onGedungClick: () -> Unit, // Klik untuk btn_gedung
    onKeratonClick: () -> Unit, // Klik untuk btn_keraton
    onKampungClick: () -> Unit, // Klik untuk btn_kampung
    onPustakaClick: () -> Unit, // Klik untuk btn_pustaka
    onBentengClick: () -> Unit // Klik untuk btn_benteng
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_jateng, null).apply {
                val searchBar: AutoCompleteTextView = findViewById(R.id.search_bar)

                val locationButtons = mapOf(
                    "lawang" to findViewById<TextView>(R.id.btn_lawang),
                    "kota" to findViewById<TextView>(R.id.btn_kota),
                    "sampokong" to findViewById<TextView>(R.id.btn_sampokong),
                    "agung" to findViewById<TextView>(R.id.btn_agung),
                    "gedung" to findViewById<TextView>(R.id.btn_gedung),
                    "keraton" to findViewById<TextView>(R.id.btn_keraton),
                    "kampung" to findViewById<TextView>(R.id.btn_kampung),
                    "pustaka" to findViewById<TextView>(R.id.btn_pustaka),
                    "benteng" to findViewById<TextView>(R.id.btn_benteng)
                )

                val places = locationButtons.keys.toList()

                // Menyembunyikan semua lokasi terlebih dahulu
                fun resetVisibility() {
                    locationButtons.values.forEach { it.visibility = View.GONE }
                }

                // Membuat ArrayAdapter untuk AutoCompleteTextView
                val adapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, places)
                searchBar.setAdapter(adapter)

                // Aksi ketika tombol Enter ditekan
                searchBar.setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                        val query = searchBar.text.toString().trim().lowercase()
                        resetVisibility() // Sembunyikan semua lokasi

                        if (locationButtons.containsKey(query)) {
                            locationButtons[query]?.visibility = View.VISIBLE
                        } else {
                            Toast.makeText(context, "Lokasi tidak ditemukan", Toast.LENGTH_SHORT).show()
                        }
                        true
                    } else {
                        false
                    }
                }

                // Menambahkan klik listener untuk tombol lokasi lainnya
                findViewById<ImageButton>(R.id.btn_location1).setOnClickListener { onLocationClick(1) }
                findViewById<ImageButton>(R.id.btn_location2).setOnClickListener { onLocationClick(2) }
                findViewById<ImageButton>(R.id.btn_location3).setOnClickListener { onLocationClick(3) }

                // Menambahkan aksi klik untuk lokasi
                locationButtons.forEach { (key, button) ->
                    button.setOnClickListener {
                        when (key) {
                            "lawang" -> onLawangClick()
                            "kota" -> onKotaClick()
                            "sampokong" -> onSampokongClick()
                            "agung" -> onAgungClick()
                            "gedung" -> onGedungClick()
                            "keraton" -> onKeratonClick()
                            "kampung" -> onKampungClick()
                            "pustaka" -> onPustakaClick()
                            "benteng" -> onBentengClick()
                        }
                    }
                }

                // Tombol untuk kembali dan profil
                findViewById<TextView>(R.id.back_button).setOnClickListener { onBackClick() }
                findViewById<ImageButton>(R.id.btn_profile).setOnClickListener { onProfileClick() }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}



@Composable
fun ShowHal10Layout(
    modifier: Modifier = Modifier,
    onLocationClick: () -> Unit, // Callback untuk lokasi
    onBackClick: () -> Unit // Callback untuk tombol kembali
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_10, null).apply {
                // Menangani klik pada tombol kembali
                findViewById<TextView>(R.id.back_button).setOnClickListener {
                    onBackClick() // Panggil callback untuk kembali
                }

                // Menangani klik pada tombol lihat lokasi
                findViewById<Button>(R.id.btn_lihat_lokasi).setOnClickListener {
                    val locationUrl = "https://maps.app.goo.gl/VGnC7MtFp7xMKK957"
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl))) // Membuka URL
                    onLocationClick() // Panggil callback setelah URL dibuka
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal11Layout(
    modifier: Modifier = Modifier,
    onLocationClick: () -> Unit, // Callback untuk lokasi
    onBackClick: () -> Unit // Callback untuk tombol kembali
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_11, null).apply {
                // Menangani klik pada tombol kembali
                findViewById<TextView>(R.id.back_button).setOnClickListener {
                    onBackClick() // Panggil callback untuk kembali
                }

                // Menangani klik pada tombol lihat lokasi
                findViewById<Button>(R.id.btn_lihat_lokasi).setOnClickListener {
                    val locationUrl = "https://maps.app.goo.gl/gnbd1M5rGqyMhmxf8"
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl))) // Membuka URL
                    onLocationClick() // Panggil callback setelah URL dibuka
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal12Layout(
    modifier: Modifier = Modifier,
    onLocationClick: () -> Unit, // Callback untuk lokasi
    onBackClick: () -> Unit // Callback untuk tombol kembali
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_12, null).apply {
                // Menangani klik pada tombol kembali
                findViewById<TextView>(R.id.back_button).setOnClickListener {
                    onBackClick() // Panggil callback untuk kembali
                }

                // Menangani klik pada tombol lihat lokasi
                findViewById<Button>(R.id.btn_lihat_lokasi).setOnClickListener {
                    val locationUrl = "https://maps.app.goo.gl/RrLjq2cFRdtxawz87"
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl))) // Membuka URL
                    onLocationClick() // Panggil callback setelah URL dibuka
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal13Layout(
    modifier: Modifier = Modifier,
    onLocationClick: () -> Unit, // Callback untuk lokasi
    onBackClick: () -> Unit // Callback untuk tombol kembali
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_13, null).apply {
                // Menangani klik pada tombol kembali
                findViewById<TextView>(R.id.back_button).setOnClickListener {
                    onBackClick() // Panggil callback untuk kembali
                }

                // Menangani klik pada tombol lihat lokasi
                findViewById<Button>(R.id.btn_lihat_lokasi).setOnClickListener {
                    val locationUrl = "https://maps.app.goo.gl/xPUpM8KggWHCXPkZ7"
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl))) // Membuka URL
                    onLocationClick() // Panggil callback setelah URL dibuka
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal14Layout(
    modifier: Modifier = Modifier,
    onLocationClick: () -> Unit, // Callback untuk lokasi
    onBackClick: () -> Unit // Callback untuk tombol kembali
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_14, null).apply {
                // Menangani klik pada tombol kembali
                findViewById<TextView>(R.id.back_button).setOnClickListener {
                    onBackClick() // Panggil callback untuk kembali
                }

                // Menangani klik pada tombol lihat lokasi
                findViewById<Button>(R.id.btn_lihat_lokasi).setOnClickListener {
                    val locationUrl = "https://maps.app.goo.gl/tPYY3d92QPySyTYT6"
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl))) // Membuka URL
                    onLocationClick() // Panggil callback setelah URL dibuka
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal15Layout(
    modifier: Modifier = Modifier,
    onLocationClick: () -> Unit, // Callback untuk lokasi
    onBackClick: () -> Unit // Callback untuk tombol kembali
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_15, null).apply {
                // Menangani klik pada tombol kembali
                findViewById<TextView>(R.id.back_button).setOnClickListener {
                    onBackClick() // Panggil callback untuk kembali
                }

                // Menangani klik pada tombol lihat lokasi
                findViewById<Button>(R.id.btn_lihat_lokasi).setOnClickListener {
                    val locationUrl = "https://maps.app.goo.gl/NkC6svpJ3aMn75KE6"
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl))) // Membuka URL
                    onLocationClick() // Panggil callback setelah URL dibuka
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHalJatengahLayout(
    modifier: Modifier = Modifier,
    onFirstImageClick: () -> Unit,  // Callback untuk tombol_lawangsewu
    onSecondImageClick: () -> Unit, // Callback untuk tombol_kota
    onSempokongClick: () -> Unit,   // Callback untuk tombol_sempokong
    onMasjidClick: () -> Unit,      // Callback untuk tombol_masjid
    onMarabuntaClick: () -> Unit,   // Callback untuk tombol_marabunta
    onKeratonClick: () -> Unit,     // Callback untuk tombol_keraton
    onBackClick: () -> Unit         // Callback untuk tombol kembali
) {
    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.hal_jatengah, null)

            // Tombol Lawang Sewu
            val firstImage: ImageView = view.findViewById(R.id.tombol_lawangsewu)
            // Tombol Kota
            val secondImage: ImageView = view.findViewById(R.id.tombol_kota)
            // Tombol Sempokong
            val sempokongButton: ImageView = view.findViewById(R.id.tombol_sempokong)
            // Tombol Masjid
            val masjidButton: ImageView = view.findViewById(R.id.tombol_masjid)
            // Tombol Marabunta
            val marabuntaButton: ImageView = view.findViewById(R.id.tombol_marabunta)
            // Tombol Keraton
            val keratonButton: ImageView = view.findViewById(R.id.tombol_keraton)
            // Tombol kembali
            val backButton: TextView = view.findViewById(R.id.back_button)

            // Tangani klik pada tombol Lawang Sewu
            firstImage.setOnClickListener {
                onFirstImageClick() // Navigasi ke `hal_10`
            }

            // Tangani klik pada tombol Kota
            secondImage.setOnClickListener {
                onSecondImageClick() // Navigasi ke `hal_11`
            }

            // Tangani klik pada tombol Sempokong
            sempokongButton.setOnClickListener {
                onSempokongClick() // Navigasi ke `hal_12`
            }

            // Tangani klik pada tombol Masjid
            masjidButton.setOnClickListener {
                onMasjidClick() // Navigasi ke `hal_13`
            }

            // Tangani klik pada tombol Marabunta
            marabuntaButton.setOnClickListener {
                onMarabuntaClick() // Navigasi ke `hal_14`
            }

            // Tangani klik pada tombol Keraton
            keratonButton.setOnClickListener {
                onKeratonClick() // Navigasi ke `hal_15`
            }

            // Tangani klik pada tombol kembali
            backButton.setOnClickListener {
                onBackClick() // Navigasi kembali ke `hal_1`
            }

            view
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHaljabarLayout(
    modifier: Modifier = Modifier,
    onLocationClick: (Int) -> Unit, // Parameter untuk lokasi spesifik
    onBackClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_jabar, null).apply {
                val searchBar: AutoCompleteTextView = findViewById(R.id.search_bar)

                val locationButtons = mapOf(
                    "gedung" to findViewById<TextView>(R.id.btn_gedung),
                    "konferensi" to findViewById<TextView>(R.id.btn_konferensi),
                    "monumen" to findViewById<TextView>(R.id.btn_monumen),
                    "saung" to findViewById<TextView>(R.id.btn_saung),
                    "geologi" to findViewById<TextView>(R.id.btn_geologi),
                    "keraton" to findViewById<TextView>(R.id.btn_keraton),
                    "kanoman" to findViewById<TextView>(R.id.btn_kanoman),
                    "goa" to findViewById<TextView>(R.id.btn_goa),
                    "masjid" to findViewById<TextView>(R.id.btn_masjid)
                )

                val places = locationButtons.keys.toList()

                // Menyembunyikan semua lokasi terlebih dahulu
                fun resetVisibility() {
                    locationButtons.values.forEach { it.visibility = View.GONE }
                }

                // Membuat ArrayAdapter untuk AutoCompleteTextView
                val adapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, places)
                searchBar.setAdapter(adapter)

                // Aksi ketika tombol Enter ditekan
                searchBar.setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                        val query = searchBar.text.toString().trim().lowercase()
                        resetVisibility() // Sembunyikan semua lokasi

                        if (locationButtons.containsKey(query)) {
                            locationButtons[query]?.visibility = View.VISIBLE
                        } else {
                            Toast.makeText(context, "Lokasi tidak ditemukan", Toast.LENGTH_SHORT).show()
                        }
                        true
                    } else {
                        false
                    }
                }

                // Menambahkan klik listener untuk tombol lokasi lainnya
                findViewById<ImageButton>(R.id.btn_location1).setOnClickListener { onLocationClick(1) }
                findViewById<ImageButton>(R.id.btn_location2).setOnClickListener { onLocationClick(2) }
                findViewById<ImageButton>(R.id.btn_location3).setOnClickListener { onLocationClick(3) }

                // Menambahkan aksi klik untuk lokasi
                locationButtons.forEach { (key, button) ->
                    button.setOnClickListener {
                        when (key) {
                            "gedung" -> onLocationClick(4)
                            "konferensi" -> onLocationClick(5)
                            "monumen" -> onLocationClick(6)
                            "saung" -> onLocationClick(7)
                            "geologi" -> onLocationClick(8)
                            "keraton" -> onLocationClick(9)
                            "kanoman" -> onLocationClick(10)
                            "goa" -> onLocationClick(11)
                            "masjid" -> onLocationClick(12)
                        }
                    }
                }

                // Tombol untuk kembali dan profil
                findViewById<TextView>(R.id.back_button).setOnClickListener { onBackClick() }
                findViewById<ImageButton>(R.id.btn_profile).setOnClickListener { onProfileClick() }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal16Layout(
    modifier: Modifier = Modifier,
    onLocationClick: () -> Unit, // Callback untuk lokasi
    onBackClick: () -> Unit // Callback untuk tombol kembali
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_16, null).apply {
                // Menangani klik pada tombol kembali
                findViewById<TextView>(R.id.back_button).setOnClickListener {
                    onBackClick() // Panggil callback untuk kembali
                }

                // Menangani klik pada tombol lihat lokasi
                findViewById<Button>(R.id.btn_lihat_lokasi).setOnClickListener {
                    val locationUrl = "https://maps.app.goo.gl/AYMdb6ktmreeESzF6"
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl))) // Membuka URL
                    onLocationClick() // Panggil callback setelah URL dibuka
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal17Layout(
    modifier: Modifier = Modifier,
    onLocationClick: () -> Unit, // Callback untuk lokasi
    onBackClick: () -> Unit // Callback untuk tombol kembali
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_17, null).apply {
                // Menangani klik pada tombol kembali
                findViewById<TextView>(R.id.back_button).setOnClickListener {
                    onBackClick() // Panggil callback untuk kembali
                }

                // Menangani klik pada tombol lihat lokasi
                findViewById<Button>(R.id.btn_lihat_lokasi).setOnClickListener {
                    val locationUrl = "https://maps.app.goo.gl/seTshCxxR5iK47dP8"
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl))) // Membuka URL
                    onLocationClick() // Panggil callback setelah URL dibuka
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal18Layout(
    modifier: Modifier = Modifier,
    onLocationClick: () -> Unit, // Callback untuk lokasi
    onBackClick: () -> Unit // Callback untuk tombol kembali
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_18, null).apply {
                // Menangani klik pada tombol kembali
                findViewById<TextView>(R.id.back_button).setOnClickListener {
                    onBackClick() // Panggil callback untuk kembali
                }

                // Menangani klik pada tombol lihat lokasi
                findViewById<Button>(R.id.btn_lihat_lokasi).setOnClickListener {
                    val locationUrl = "https://maps.app.goo.gl/Fy8SvRMXs7JCyBje6"
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl))) // Membuka URL
                    onLocationClick() // Panggil callback setelah URL dibuka
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal19Layout(
    modifier: Modifier = Modifier,
    onLocationClick: () -> Unit, // Callback untuk lokasi
    onBackClick: () -> Unit // Callback untuk tombol kembali
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_19, null).apply {
                // Menangani klik pada tombol kembali
                findViewById<TextView>(R.id.back_button).setOnClickListener {
                    onBackClick() // Panggil callback untuk kembali
                }

                // Menangani klik pada tombol lihat lokasi
                findViewById<Button>(R.id.btn_lihat_lokasi).setOnClickListener {
                    val locationUrl = "https://maps.app.goo.gl/xWzrgbkStwLucUR16"
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl))) // Membuka URL
                    onLocationClick() // Panggil callback setelah URL dibuka
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal20Layout(
    modifier: Modifier = Modifier,
    onLocationClick: () -> Unit, // Callback untuk lokasi
    onBackClick: () -> Unit // Callback untuk tombol kembali
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_20, null).apply {
                // Menangani klik pada tombol kembali
                findViewById<TextView>(R.id.back_button).setOnClickListener {
                    onBackClick() // Panggil callback untuk kembali
                }

                // Menangani klik pada tombol lihat lokasi
                findViewById<Button>(R.id.btn_lihat_lokasi).setOnClickListener {
                    val locationUrl = "https://maps.app.goo.gl/qrZ2QMseC8MsBDUe9"
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl))) // Membuka URL
                    onLocationClick() // Panggil callback setelah URL dibuka
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal21Layout(
    modifier: Modifier = Modifier,
    onLocationClick: () -> Unit, // Callback untuk lokasi
    onBackClick: () -> Unit // Callback untuk tombol kembali
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_21, null).apply {
                // Menangani klik pada tombol kembali
                findViewById<TextView>(R.id.back_button).setOnClickListener {
                    onBackClick() // Panggil callback untuk kembali
                }

                // Menangani klik pada tombol lihat lokasi
                findViewById<Button>(R.id.btn_lihat_lokasi).setOnClickListener {
                    val locationUrl = "https://maps.app.goo.gl/XjmhBjEzcypGUd1cA"
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl))) // Membuka URL
                    onLocationClick() // Panggil callback setelah URL dibuka
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHalJabaratLayout(
    modifier: Modifier = Modifier,
    onGedungSateClick: () -> Unit, // Callback untuk tombol gedung sate
    onKonferensiClick: () -> Unit, // Callback untuk tombol konferensi
    onMonumenClick: () -> Unit,    // Callback untuk tombol monumen
    onSaungClick: () -> Unit,      // Callback untuk tombol saung
    onGeologiClick: () -> Unit,    // Callback untuk tombol geologi
    onKesepuhanClick: () -> Unit,  // Callback untuk tombol kesepuhan
    onBackClick: () -> Unit        // Callback untuk tombol kembali
) {
    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.hal_jabarat, null)

            // Tangkap semua elemen view di layout
            val gedungSateImage: ImageView = view.findViewById(R.id.btn_gedung_sate)
            val konferensiImage: ImageView = view.findViewById(R.id.btn_konferensi)
            val monumenImage: ImageView = view.findViewById(R.id.btn_monumen)
            val saungImage: ImageView = view.findViewById(R.id.btn_saung)
            val geologiImage: ImageView = view.findViewById(R.id.btn_geologi)
            val kesepuhanImage: ImageView = view.findViewById(R.id.btn_kesepuhan)
            val backButton: TextView = view.findViewById(R.id.back_button) // Tombol kembali

            // Logika callback masing-masing tombol
            gedungSateImage.setOnClickListener { onGedungSateClick() }
            konferensiImage.setOnClickListener { onKonferensiClick() }
            monumenImage.setOnClickListener { onMonumenClick() }
            saungImage.setOnClickListener { onSaungClick() }
            geologiImage.setOnClickListener { onGeologiClick() }
            kesepuhanImage.setOnClickListener { onKesepuhanClick() }

            // Tambahkan logika tombol kembali
            backButton.setOnClickListener {
                onBackClick() // Callback untuk tombol kembali
            }

            view
        },
        modifier = modifier.fillMaxSize()
    )
}
