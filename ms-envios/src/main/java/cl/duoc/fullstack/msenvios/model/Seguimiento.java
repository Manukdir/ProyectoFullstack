package cl.duoc.fullstack.msenvios.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "seguimientos")
public class Seguimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "envio_id", nullable = false)
    private Envio envio;

    @Column(name = "ubicacion_actual", nullable = false, length = 150)
    private String ubicacionActual;

    @Column(name = "estado", nullable = false, length = 150)
    private String estado;

    @Column(name = "orden_evento", nullable = false)
    private Integer ordenEvento;

    @Column(name = "visible_cliente", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean visibleCliente;

    @Column(name = "fecha_evento", nullable = false)
    private LocalDateTime fechaEvento;

    @Column(name = "comentario", nullable = false, length = 150)
    private String comentario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public String getUbicacionActual() {
        return ubicacionActual;
    }

    public void setUbicacionActual(String ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getOrdenEvento() {
        return ordenEvento;
    }

    public void setOrdenEvento(Integer ordenEvento) {
        this.ordenEvento = ordenEvento;
    }

    public boolean isVisibleCliente() {
        return visibleCliente;
    }

    public void setVisibleCliente(boolean visibleCliente) {
        this.visibleCliente = visibleCliente;
    }

    public LocalDateTime getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(LocalDateTime fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
