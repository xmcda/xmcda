package io.github.oliviercailloux.y2016.xmcda.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import io.github.oliviercailloux.y2016.xmcda.dao.DAOException;
import io.github.oliviercailloux.y2016.xmcda.dao.UserDao;
import io.github.oliviercailloux.y2016.xmcda.entities.User;

public class LoginForm {

	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_PASS = "motdepasse";
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private UserDao userDao1;

	public LoginForm(UserDao utilisateurDao) {

		this.userDao1 = utilisateurDao;
	}

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public User login(HttpServletRequest request) throws Exception {
		String email = getValeurChamp(request, CHAMP_EMAIL);
		String motDePasse = getValeurChamp(request, CHAMP_PASS);
		System.out.println("dans le loginForm methode login : " + email + motDePasse);

		User login = new User();
		try {
			login.setEmail(email);
			login.setMotDePasse(motDePasse);
			System.out.println("je suis dans la methode login, voia l email et le mot de passe" + login.getEmail()
					+ login.getMotDePasse());
			if (erreurs.isEmpty()) {
				System.out.println("je vais tester la trouver login dans le dao");
				if (userDao1.trouverLogin(login.getEmail(), login.getMotDePasse()) != 0) {
					System.out.println("loginform oui j existe");
					return login;
				} else {
					System.out.println("il n'existe pas");
					return null;
				}
			}
		} catch (DAOException e) {
			resultat = "Echec de l'inscription : une erreur  est survenue.";
			e.printStackTrace();
		}
		return null;
	}

	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {

			return valeur.trim();
		}
	}

}
