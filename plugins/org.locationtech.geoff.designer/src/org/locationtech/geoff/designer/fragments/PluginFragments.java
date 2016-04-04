/*******************************************************************************
 * Copyright (c) 2016 Erdal Karaca and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Erdal Karaca - initial API and implementation
 *******************************************************************************/
package org.locationtech.geoff.designer.fragments;

import org.locationtech.geoff.e4.utils.fragments.FragmentBuilder;
import org.locationtech.geoff.e4.utils.fragments.ModelFragmentsProvider;
import org.locationtech.geoff.ui.UIConsts;
import org.locationtech.geoff.ui.handlers.AddNewLayerHandler;
import org.locationtech.geoff.ui.handlers.AddResourceAsLayerHandler;
import org.locationtech.geoff.ui.handlers.AddTileLayerHandler;
import org.locationtech.geoff.ui.handlers.DeleteLayerHandler;
import org.locationtech.geoff.ui.handlers.RedoHandler;
import org.locationtech.geoff.ui.handlers.UndoHandler;
import org.locationtech.geoff.ui.parts.LayersUI;
import org.locationtech.geoff.ui.parts.MapPropsUI;

public class PluginFragments extends ModelFragmentsProvider {
	private static final String ICONS_GEOFF_16_PNG = "icons/geoff-16.png";
	private static final String ICONS_LAYERS_16_PNG = "icons/layers-16.png";
	private static final String GEOFF_DESIGNER = "Geoff Designer";
	private static final String PERSPECTIVE_DESIGNER = "org.locationtech.geoff.designer.perspective";
	private static final String CATEGORY_GEOFF_DESIGNER = "org.locationtech.geoff.designer.commands.category";
	private static final String COMMAND_ADD_RESOURCE_AS_LAYER = "org.locationtech.geoff.designer.commands.addResourceAsLayer";
	private static final String COMMAND_ADD_TILE_LAYER = "org.locationtech.geoff.designer.commands.addTileLayer";
	private static final String COMMAND_ADD_NEW_LAYER = "org.locationtech.geoff.designer.commands.addNewLayer";

	public void fragmentCategories(FragmentBuilder b) {
		b.customize(f -> {
			f.setParentElementId(ANY_APPLICATION);
			f.setFeaturename("categories");
		}).element(FCOMM::createCategory, (cat, cb) -> {
			cat.setElementId(CATEGORY_GEOFF_DESIGNER);
			cat.setName(GEOFF_DESIGNER);
		});
	}

	public void fragmentCommands(FragmentBuilder b) {
		b.customize(f -> {
			f.setParentElementId(ANY_APPLICATION);
			f.setFeaturename("commands");
		}).element(FCOMM::createCommand, (cmd, cb) -> {
			cmd.setElementId(COMMAND_ADD_RESOURCE_AS_LAYER);
			cmd.setCommandName("Add Resource Layer");
			cmd.setDescription("Adds selected resource as new layer");
			cmd.setCategory(categoryRef(CATEGORY_GEOFF_DESIGNER));
		}).element(FCOMM::createCommand, (cmd, cb) -> {
			cmd.setElementId(COMMAND_ADD_TILE_LAYER);
			cmd.setCommandName("Add Tile Layer");
			cmd.setCategory(categoryRef(CATEGORY_GEOFF_DESIGNER));

			cb.child(FCOMM::createCommandParameter, (cp, cpb) -> {
				cp.setElementId("tileProvider");
				cp.setName("Tile Provider");
			});
		}).element(FCOMM::createCommand, (cmd, cb) -> {
			cmd.setElementId(COMMAND_ADD_NEW_LAYER);
			cmd.setCommandName("Add New Layer");
			cmd.setCategory(categoryRef(CATEGORY_GEOFF_DESIGNER));
		});
	}

	public void fragmentHandlers(FragmentBuilder b) {
		b.customize(f -> {
			f.setParentElementId(ANY_APPLICATION);
			f.setFeaturename("handlers");
		}).element(FCOMM::createHandler, (handler, handlerBuilder) -> {
			handler.setContributionURI(toBundleclassURI(AddResourceAsLayerHandler.class));
			handler.setCommand(commandRef(COMMAND_ADD_RESOURCE_AS_LAYER));
		}).element(FCOMM::createHandler, (handler, handlerBuilder) -> {
			handler.setContributionURI(toBundleclassURI(AddTileLayerHandler.class));
			handler.setCommand(commandRef(COMMAND_ADD_TILE_LAYER));
		}).element(FCOMM::createHandler, (handler, handlerBuilder) -> {
			handler.setContributionURI(toBundleclassURI(AddNewLayerHandler.class));
			handler.setCommand(commandRef(COMMAND_ADD_NEW_LAYER));
		});

		b.element(FCOMM::createHandler, (handler, handlerBuilder) -> {
			handler.setContributionURI(toBundleclassURI(UndoHandler.class));
			handler.setCommand(commandRef(UIConsts.EDIT_UNDO));
		}).element(FCOMM::createHandler, (handler, handlerBuilder) -> {
			handler.setContributionURI(toBundleclassURI(RedoHandler.class));
			handler.setCommand(commandRef(UIConsts.EDIT_REDO));
		}).element(FCOMM::createHandler, (handler, handlerBuilder) -> {
			handler.setContributionURI(toBundleclassURI(DeleteLayerHandler.class));
			handler.setCommand(commandRef(UIConsts.EDIT_DELETE));
		});
	}

	public void fragmentPartDescriptors(FragmentBuilder b) {
		b.customize(f -> {
			f.setParentElementId(ANY_APPLICATION);
			f.setFeaturename("descriptors");
		}).element(FDESC::createPartDescriptor, (p, pBuilder) -> {
			p.setElementId(LayersUI.class.getName());
			p.setIconURI(toPlatformURI(ICONS_LAYERS_16_PNG));
			p.setLabel("Layers");
			p.setContributionURI(toBundleclassURI(LayersUI.class));
			p.setAllowMultiple(false);
			setupForContributions(p);
		}).element(FDESC::createPartDescriptor, (p, pBuilder) -> {
			p.setElementId(MapPropsUI.class.getName());
			p.setIconURI(toPlatformURI(ICONS_LAYERS_16_PNG));
			p.setLabel("Properties");
			p.setContributionURI(toBundleclassURI(MapPropsUI.class));
			p.setAllowMultiple(false);
			setupForContributions(p);
		});
	}

	// public void fragmentPerspectives(FragmentBuilder b) {
	// b.customize(f -> {
	// f.setParentElementId(ANY_PERSPECTIVE_STACK);
	// f.setFeaturename("children");
	// }).element(FADV::createPerspective, (p, pBuilder) -> {
	// p.setElementId(PERSPECTIVE_DESIGNER);
	// p.setIconURI(toPlatformURI(ICONS_GEOFF_16_PNG));
	// p.setLabel(GEOFF_DESIGNER);
	// });
	// }
}
