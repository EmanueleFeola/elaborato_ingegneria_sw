package elaborato_ing_sw.model;

import java.io.Serializable;
import java.time.LocalDate;

public class FidelityCard implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private LocalDate emissionDate;
	private double pointsTot;
	private User user;

	public FidelityCard(int id, LocalDate emissionDate, double pointTot, User user) {
		this.id = id;
		this.emissionDate = emissionDate;
		this.pointsTot = pointTot;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getEmissionDate() {
		return emissionDate;
	}

	public void setEmissionDate(LocalDate emissionDate) {
		this.emissionDate = emissionDate;
	}

	public double getPointsTot() {
		return pointsTot;
	}

	public void setPointsTot(double pointsTot) {
		this.pointsTot = pointsTot;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Fidelity Card [id=" + id + ", emissionDate=" + emissionDate + ", pointsTot=" + pointsTot + ", user="
				+ user + "]";
	}
}
