package com.tgf.ecoapp.ui.recycle;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tgf.ecoapp.R;
import java.util.ArrayList;
import java.util.List;

public class RecycleFragment extends Fragment implements ContainerAdapter.OnContainerClickListener {

    private List<Container> containerList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycle, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Define your containers here
        containerList = new ArrayList<>();
        containerList.add(new Container("Contenedor gris", "Es el que sirve para depositar los residuos orgánicos, como restos de comida, así como otro tipo de residuos como pañales, papel sucio, bastoncillos…", R.drawable.contenedor_gris));
        containerList.add(new Container("Contenedor amarillo", "Es el encargado del reciclaje de los plásticos, tetra brik y el metal. Recuerde que los tetra brik se fabrican a partir de finas capas de celulosa, aluminio y plásticos, que son muy difíciles de reciclar.", R.drawable.contenedor_amarillo));
        containerList.add(new Container("Contenedor azul", "Es el que recoge el papel y el cartón que no estén sucios. A los sobres hay que quitarle las ventanillas de plástico y a los cuadernos las espirales.", R.drawable.contenedor_azul));
        containerList.add(new Container("Contenedor verde", "Es el encargado del vidrio, siempre sin tapas de metal o de corcho y siempre que el vidrio sea utilizado para envasar alimentos, no así cristales de ventanas, vidrios planos o vasos rotos, que por su alto contenido en plomo deben ir al punto limpio para ser tratados en otra cadena de reciclaje.", R.drawable.contenedor_verde));
        containerList.add(new Container("Contenedor marrón", "En algunos municipios de la Comunidad de Madrid está implementado el contenedor marrón, en el que se despositan restos orgánicos de comida de origen animal (carne, pescado, espinas, queso o cáscaras de huevo) o vegetal (verduras, frutas, semillas, cáscaras), restos de pan, posos de café, bolsas de infusiones, cerillas o tapones de corcho.", R.drawable.contenedor_marron));

        ContainerAdapter adapter = new ContainerAdapter(containerList, this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onContainerClick(int position) {
        Container container = containerList.get(position);
        // Navigate to ContainerDetailFragment
        ContainerDetailFragment fragment = ContainerDetailFragment.newInstance(container);
        getParentFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
    }
}