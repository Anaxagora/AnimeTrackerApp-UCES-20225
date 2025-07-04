// Generated by view binder compiler. Do not edit!
package com.example.animetrackerapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.animetrackerapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityEditarBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final MaterialButton btnActualizar;

  @NonNull
  public final LinearLayout containerEditar;

  @NonNull
  public final TextInputEditText etAnioEditar;

  @NonNull
  public final TextInputEditText etComentarioEditar;

  @NonNull
  public final TextInputEditText etNombreEditar;

  @NonNull
  public final ImageView fondo;

  @NonNull
  public final TextInputLayout inputLayoutAnioEditar;

  @NonNull
  public final TextInputLayout inputLayoutComentarioEditar;

  @NonNull
  public final TextInputLayout inputLayoutNombreEditar;

  @NonNull
  public final Slider sliderValoracionEditar;

  @NonNull
  public final Spinner spinnerEstadoEditar;

  @NonNull
  public final Spinner spinnerGeneroEditar;

  private ActivityEditarBinding(@NonNull FrameLayout rootView,
      @NonNull MaterialButton btnActualizar, @NonNull LinearLayout containerEditar,
      @NonNull TextInputEditText etAnioEditar, @NonNull TextInputEditText etComentarioEditar,
      @NonNull TextInputEditText etNombreEditar, @NonNull ImageView fondo,
      @NonNull TextInputLayout inputLayoutAnioEditar,
      @NonNull TextInputLayout inputLayoutComentarioEditar,
      @NonNull TextInputLayout inputLayoutNombreEditar, @NonNull Slider sliderValoracionEditar,
      @NonNull Spinner spinnerEstadoEditar, @NonNull Spinner spinnerGeneroEditar) {
    this.rootView = rootView;
    this.btnActualizar = btnActualizar;
    this.containerEditar = containerEditar;
    this.etAnioEditar = etAnioEditar;
    this.etComentarioEditar = etComentarioEditar;
    this.etNombreEditar = etNombreEditar;
    this.fondo = fondo;
    this.inputLayoutAnioEditar = inputLayoutAnioEditar;
    this.inputLayoutComentarioEditar = inputLayoutComentarioEditar;
    this.inputLayoutNombreEditar = inputLayoutNombreEditar;
    this.sliderValoracionEditar = sliderValoracionEditar;
    this.spinnerEstadoEditar = spinnerEstadoEditar;
    this.spinnerGeneroEditar = spinnerGeneroEditar;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityEditarBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityEditarBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_editar, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityEditarBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnActualizar;
      MaterialButton btnActualizar = ViewBindings.findChildViewById(rootView, id);
      if (btnActualizar == null) {
        break missingId;
      }

      id = R.id.containerEditar;
      LinearLayout containerEditar = ViewBindings.findChildViewById(rootView, id);
      if (containerEditar == null) {
        break missingId;
      }

      id = R.id.etAnioEditar;
      TextInputEditText etAnioEditar = ViewBindings.findChildViewById(rootView, id);
      if (etAnioEditar == null) {
        break missingId;
      }

      id = R.id.etComentarioEditar;
      TextInputEditText etComentarioEditar = ViewBindings.findChildViewById(rootView, id);
      if (etComentarioEditar == null) {
        break missingId;
      }

      id = R.id.etNombreEditar;
      TextInputEditText etNombreEditar = ViewBindings.findChildViewById(rootView, id);
      if (etNombreEditar == null) {
        break missingId;
      }

      id = R.id.fondo;
      ImageView fondo = ViewBindings.findChildViewById(rootView, id);
      if (fondo == null) {
        break missingId;
      }

      id = R.id.inputLayoutAnioEditar;
      TextInputLayout inputLayoutAnioEditar = ViewBindings.findChildViewById(rootView, id);
      if (inputLayoutAnioEditar == null) {
        break missingId;
      }

      id = R.id.inputLayoutComentarioEditar;
      TextInputLayout inputLayoutComentarioEditar = ViewBindings.findChildViewById(rootView, id);
      if (inputLayoutComentarioEditar == null) {
        break missingId;
      }

      id = R.id.inputLayoutNombreEditar;
      TextInputLayout inputLayoutNombreEditar = ViewBindings.findChildViewById(rootView, id);
      if (inputLayoutNombreEditar == null) {
        break missingId;
      }

      id = R.id.sliderValoracionEditar;
      Slider sliderValoracionEditar = ViewBindings.findChildViewById(rootView, id);
      if (sliderValoracionEditar == null) {
        break missingId;
      }

      id = R.id.spinnerEstadoEditar;
      Spinner spinnerEstadoEditar = ViewBindings.findChildViewById(rootView, id);
      if (spinnerEstadoEditar == null) {
        break missingId;
      }

      id = R.id.spinnerGeneroEditar;
      Spinner spinnerGeneroEditar = ViewBindings.findChildViewById(rootView, id);
      if (spinnerGeneroEditar == null) {
        break missingId;
      }

      return new ActivityEditarBinding((FrameLayout) rootView, btnActualizar, containerEditar,
          etAnioEditar, etComentarioEditar, etNombreEditar, fondo, inputLayoutAnioEditar,
          inputLayoutComentarioEditar, inputLayoutNombreEditar, sliderValoracionEditar,
          spinnerEstadoEditar, spinnerGeneroEditar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
