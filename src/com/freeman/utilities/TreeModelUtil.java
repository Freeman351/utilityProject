package com.freeman.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.freeman.model.TreeEntity;
import com.freeman.model.UserLabel;
import com.jgeppert.struts2.jquery.tree.result.TreeNode;

public class TreeModelUtil {

	public static List<TreeEntity> generateUserLabelTreeEntityList(
			List<UserLabel> userLabels) {
		if (userLabels == null || userLabels.size() == 0)
			return null;
		List<TreeEntity> treeNodes = new ArrayList<TreeEntity>();
		for (UserLabel userLabel : userLabels) {
			if (userLabel != null) {
				TreeEntity treeNode =  generateTreeEntity(userLabel);
				treeNodes.add(treeNode);
			}
		}
		return treeNodes;
	}

	public static Map<Long, TreeEntity> generateUserLabelTreeEntityMap(
			List<UserLabel> userLabels) {
		if (userLabels == null || userLabels.size() == 0)
			return null;
		Map<Long, TreeEntity> TreeEntityMap = new HashMap<Long, TreeEntity>();
		for (UserLabel userLabel : userLabels) {
			if (userLabel != null && userLabel.getId() != null) {
				TreeEntity treeNode = generateTreeEntity(userLabel);
				TreeEntityMap.put(userLabel.getId(), treeNode);
			}
		}
		return TreeEntityMap;
	}

	public static List<TreeEntity> generateIncludeTreeEntityList(Map<Long, TreeEntity> treeEntityMap, 
			List<Long> includeIds, String indentation, List<TreeEntity> forest) {

		if (includeIds == null || includeIds.isEmpty())
			return null;
		if (treeEntityMap == null || treeEntityMap.isEmpty())
			return null;
		List<TreeEntity> includeList = new ArrayList<TreeEntity>();
		for (Long id : includeIds) {
			if (treeEntityMap.containsKey(id)) {
				TreeEntity node = TreeEntityUtil.findTreeEntityInForest(forest,
						treeEntityMap.get(id));
				if (node == null)
					continue;
				StringBuilder nameBuilder = new StringBuilder();
				for (int i = 0; i < node.getLevel(); i++) {
					nameBuilder.append(indentation);
				}
				nameBuilder.append(treeEntityMap.get(id).getName());
				includeList.add(new TreeEntity(id,  treeEntityMap.get(id).getParentId(), treeEntityMap.get(id).getRootId(), nameBuilder.toString()));
			}
		}
		return (includeList.isEmpty() ? null : includeList);

	}
	
	public static LinkedHashMap<Long, TreeEntity> generateIncludeTreeEntityMap(
			Map<Long, TreeEntity> treeEntityMap, List<Long> includeIds) {
		if (includeIds == null || includeIds.isEmpty())
			return null;
		if (treeEntityMap == null || treeEntityMap.isEmpty())
			return null;
		LinkedHashMap<Long, TreeEntity> includeMap = new LinkedHashMap<Long, TreeEntity>();
		for (Long id : includeIds) {
			if (treeEntityMap.containsKey(id)) {
				includeMap.put(id, new TreeEntity(id, treeEntityMap.get(id)
						.getParentId(), treeEntityMap.get(id).getRootId(),
						treeEntityMap.get(id).getName()));
			}
		}
		return (includeMap.isEmpty() ? null : includeMap);
	}

	public static LinkedHashMap<Long, String> generateUserLabelNameMap(
			Map<Long, TreeEntity> treeEntityMap, List<Long> includeIds,
			String indentation, List<TreeEntity> forest) {

		if (includeIds == null || includeIds.isEmpty())
			return null;
		if (treeEntityMap == null || treeEntityMap.isEmpty())
			return null;
		LinkedHashMap<Long, String> dropdownMap = new LinkedHashMap<Long, String>();
		for (Long id : includeIds) {
			if (treeEntityMap.containsKey(id)) {
				int nodeLevel = TreeEntityUtil.findTreeEntityLevel(forest,
						treeEntityMap.get(id));
				StringBuilder nameBuilder = new StringBuilder();
				for (int i = 0; i < nodeLevel; i++) {
					nameBuilder.append(indentation);
				}
				nameBuilder.append(treeEntityMap.get(id).getName());
				dropdownMap.put(id, nameBuilder.toString());
			}
		}
		return (dropdownMap.isEmpty() ? null : dropdownMap);

	}

	public static LinkedHashMap<Long, String> generateUserLabelNameMap(
			Map<Long, TreeEntity> treeEntityMap, List<Long> includeIds,
			String indentation) {

		if (includeIds == null || includeIds.isEmpty())
			return null;
		if (treeEntityMap == null || treeEntityMap.isEmpty())
			return null;
		LinkedHashMap<Long, String> dropdownMap = new LinkedHashMap<Long, String>();
		for (Long id : includeIds) {
			if (treeEntityMap.containsKey(id)) {
				dropdownMap.put(id, treeEntityMap.get(id).getName());
			}
		}
		return (dropdownMap.isEmpty() ? null : dropdownMap);

	}
	
	public static List<Long> generateAncestorUserLabelIds(List<UserLabel> userLabels, Map<Long, UserLabel> relatedUserLabelMap, List<Long> relatedIds){
		List<TreeEntity> treeEntityList = TreeModelUtil.generateUserLabelTreeEntityList(userLabels);
		List<TreeEntity> forest = TreeEntityUtil.generateForest(treeEntityList);
		List<Long> relatedRootIds = new ArrayList<Long>();
		for (Long id : relatedIds){
			if (relatedUserLabelMap.containsKey(id)){
				relatedRootIds.add(relatedUserLabelMap.get(id).getRootUserLabelId());
			}
		}		
		return TreeEntityUtil.getAncestorIds(forest, relatedIds, relatedRootIds);
	}
	
	public static List<TreeNode> generateDisplayTree(List<TreeEntity> subNodes){
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		if (subNodes == null || subNodes.isEmpty())
			return nodes;
		for (TreeEntity entity : subNodes ){
			TreeNode node = new TreeNode();
			node.setId("" + entity.getId());
			node.setTitle(entity.getName());
			if (entity.getChildren() == null || entity.getChildren().isEmpty()){
				node.setState(TreeNode.NODE_STATE_LEAF);							
			}else{
				node.setState(TreeNode.NODE_STATE_CLOSED);
			}
			nodes.add(node);			
		}
			
		return nodes;
	}

	public static TreeEntity generateTreeEntity(UserLabel userLabel){
		
		return new TreeEntity(userLabel.getId(),userLabel.getParentUserLabelId(),
				userLabel.getRootUserLabelId(), userLabel.getLabel().getLabelName());
	}
}
