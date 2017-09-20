package com.freeman.utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.freeman.model.TreeEntity;

public class TreeEntityUtil {
	public static List<TreeEntity> generateForest(List<TreeEntity> entityList) {

		List<TreeEntity> forest = new ArrayList<TreeEntity>();
		List<TreeEntity> descendants = new ArrayList<TreeEntity>();

		for (TreeEntity entity : entityList) {
			if (null == entity.getParentId()) {
				TreeEntity result = entity;
				forest.add(result);
			} else {
				descendants.add(entity);
			}
		}
		buildTree(forest, descendants);

		return forest;
	}

	public static List<TreeEntity> generateForest(
			Map<Long, TreeEntity> entityMap) {

		List<TreeEntity> forest = new ArrayList<TreeEntity>();
		List<TreeEntity> descendants = new ArrayList<TreeEntity>();

		for (Map.Entry<Long, TreeEntity> entity : entityMap.entrySet()) {
			if (entity.getValue().getParentId() == null) {
				TreeEntity result = entity.getValue();
				forest.add(result);
			} else {
				descendants.add(entity.getValue());

			}
		}

		buildTree(forest, descendants);

		return forest;
	}

	private static void buildTree(List<TreeEntity> parents,
			List<TreeEntity> descendants) {

		List<TreeEntity> nextLevelparents = new ArrayList<TreeEntity>();

		for (Iterator<TreeEntity> parentIt = parents.iterator(); parentIt
				.hasNext();) {
			TreeEntity parent = parentIt.next();
			for (Iterator<TreeEntity> descendantIt = descendants.iterator(); descendantIt
					.hasNext();) {
				TreeEntity descendant = descendantIt.next();
				if (parent.getId().equals(descendant.getParentId())) {
					if (null == parent.getChildren()) {
						parent.setChildren(new ArrayList<TreeEntity>());
					}
					parent.getChildren().add(descendant);
					nextLevelparents.add(descendant);
					descendantIt.remove();
				}
			}
			if (descendants.size() == 0) {
				return;
			} else {
				buildTree(nextLevelparents, descendants);
			}
		}
	}

	public static TreeEntity getCurrentTree(List<TreeEntity> forest, Long rootId) {
		for (TreeEntity tree : forest) {
			if (tree != null && tree.getRootId().equals(rootId))
				return tree;
		}
		return null;
	}

	public static TreeEntity findTreeEntityInForest(List<TreeEntity> forest,
			TreeEntity entity) {
		if (entity == null || entity.getId() == null
				|| entity.getRootId() == null)
			return null;
		TreeEntity tree = getCurrentTree(forest, entity.getRootId());
		if (tree == null)
			return null;
		TreeEntity node = findTreeEntityById(tree, entity.getId());
		if (node == null)
			return null;
		return node;
	}
	
	public static int findTreeEntityLevel(List<TreeEntity> forest,
			TreeEntity entity) {
		if (entity == null || entity.getId() == null
				|| entity.getRootId() == null)
			return -1;
		TreeEntity tree = getCurrentTree(forest, entity.getRootId());
		if (tree == null)
			return -1;
		TreeEntity node = findTreeEntityById(tree, entity.getId());
		if (node == null)
			return -1;
		return node.getLevel();
	}

	public static TreeEntity findTreeEntityById(TreeEntity tree, Long entityId) {
		if (tree.getId().equals(entityId))
			return tree;
		if (tree.getChildren() == null || tree.getChildren().isEmpty())
			return null;
		for (TreeEntity child : tree.getChildren()) {
			TreeEntity resultEntity = findTreeEntityById(child, entityId);
			if (resultEntity != null) {
				return resultEntity;
			}
		}
		return null;
	}

	public static List<TreeEntity> getJuniors(TreeEntity tree) {
		List<TreeEntity> juniorList = new ArrayList<TreeEntity>();
		if (tree.getChildren() == null)
			return null;
		for (TreeEntity junior : tree.getChildren()) {
			if (junior != null)
				juniorList.add(junior);
			if (getJuniors(junior) != null && !getJuniors(junior).isEmpty())
				juniorList.addAll(getJuniors(junior));
		}
		return juniorList;
	}

	public static List<Long> getJuniorIdsByCurrentId(TreeEntity tree,
			Long currentId) {
		if (currentId == null)
			return new ArrayList<Long>();
		if (tree == null || tree.getChildren() == null
				|| tree.getChildren().isEmpty())
			return new ArrayList<Long>();
		TreeEntity subTree = findTreeEntityById(tree, currentId);
		List<TreeEntity> juniors = getJuniors(subTree);
		if (juniors == null || juniors.isEmpty())
			return new ArrayList<Long>();
		List<Long> juniorIds = new ArrayList<Long>();
		for (TreeEntity junior : juniors) {
			if (junior != null && junior.getId() != null)
				juniorIds.add(junior.getId());
		}
		return juniorIds;
	}

	public static List<Long> getJuniorIds(List<TreeEntity> treeNodes,
			Long currentId, Long rootId) {
		if (currentId == null)
			return null;
		if (rootId == null)
			return null;
		if (treeNodes == null || treeNodes.isEmpty())
			return null;
		// generate forest -- TreeEntity
		List<TreeEntity> forest = TreeEntityUtil.generateForest(treeNodes);
		List<Long> juniorIds = TreeEntityUtil.getJuniorIdsInForest(forest, currentId, rootId);		
		return juniorIds;

	}

	public static List<Long> getJuniorIds(Map<Long, TreeEntity> treeEntityMap,
			Long currentId, Long rootId) {
		if (currentId == null)
			return null;
		if (rootId == null)
			return null;
		if (treeEntityMap == null || treeEntityMap.isEmpty())
			return null;
		// generate forest -- TreeEntity
		List<TreeEntity> forest = TreeEntityUtil.generateForest(treeEntityMap);
		List<Long> juniorIds = TreeEntityUtil.getJuniorIdsInForest(forest, currentId, rootId);
		return juniorIds;

	}

	public static List<Long> getJuniorIdsInForest(List<TreeEntity> forest, Long currentId, Long rootId) {
		if (currentId == null)
			return null;
		if (rootId == null)
			return null;
		if (forest == null )
			return null;
		// get current tree
		TreeEntity tree = TreeEntityUtil.getCurrentTree(forest, rootId);
		// get current node's junior ids
		List<Long> juniorIds = TreeEntityUtil.getJuniorIdsByCurrentId(tree,
				currentId);
		return juniorIds;

	}
	
	public static List<Long> traverseForest(List<TreeEntity> forest) {
		List<Long> idList = new ArrayList<Long>();
		for (TreeEntity tree : forest) {
			idList.addAll(traverseTree(tree));
		}
		return idList;
	}

	public static List<Long> traverseTree(TreeEntity tree) {
		List<Long> idList = new ArrayList<Long>();
		if (tree == null || tree.getId() == null)
			return idList;
		idList.add(tree.getId());
		if (tree.getChildren() == null || tree.getChildren().isEmpty())
			return idList;
		for (TreeEntity subTree : tree.getChildren()) {
			idList.addAll(traverseTree(subTree));
		}
		return idList;
	}

	public static List<Long> getAncestorIds(TreeEntity tree, Long nodeId){
		List<Long> idList = new ArrayList<Long>();
		if (!idList.contains(nodeId))
			idList.add(nodeId);
		TreeEntity node = findTreeEntityById(tree, nodeId);
		if (node == null || node.getParentId() == null)
			return idList;
		idList.add(0, node.getParentId());
		idList.addAll(0, getAncestorIds(tree, node.getParentId()));
		return idList;
	}
	
	public static List<Long> getAncestorIds(List<TreeEntity> forest, List<Long> ids, List<Long> rootIds){
		List<Long> idList = new ArrayList<Long>();
		if (forest == null || ids == null || ids.isEmpty() || rootIds == null 
				|| rootIds.isEmpty() || ids.size() != rootIds.size())
			return idList;
		for (int i = 0; i < rootIds.size(); i++){
			TreeEntity tree = getCurrentTree(forest, rootIds.get(i));
			List<Long> parentIds = getAncestorIds(tree, ids.get(i));
			for (Long parentId : parentIds){
				if (!idList.contains(parentId))
					idList.add(parentId);
			}
		}
		return idList;
	}

	public static List<TreeEntity> generateNodesLevel(List<TreeEntity> forest) {
		for (TreeEntity tree : forest) {
			tree = generateNodesLevel(tree);
		}
		return forest;
	}

	public static TreeEntity generateNodesLevel(TreeEntity tree) {
		List<Long> idList = traverseTree(tree);
		for (Long id : idList) {
			TreeEntity node = findTreeEntityById(tree, id);
			if (node.getParentId() == null) {
				node.setLevel(0);
			} else {
				TreeEntity parentNode = findTreeEntityById(tree,
						node.getParentId());
				node.setLevel(parentNode.getLevel() + 1);
			}
		}
		return tree;
	}

	public static void main(String[] args) {

		TreeEntity ent0 = new TreeEntity(0L, null, 0L, "ALL");
		TreeEntity ent1 = new TreeEntity(1L, 0L, 0L, "China");
		TreeEntity ent2 = new TreeEntity(11L, 1L, 0L, "YY");
		TreeEntity ent3 = new TreeEntity(111L, 11L, 0L, "XX");
		TreeEntity ent6 = new TreeEntity(1111L, 111L, 0L, "ZZ");
		TreeEntity ent4 = new TreeEntity(2L, 0L, 0L, "USA");
		TreeEntity ent5 = new TreeEntity(21L, 2L, 0L, "Toronto");
		TreeEntity ent7 = new TreeEntity(7L, null, 7L, "USA");
		TreeEntity ent8 = new TreeEntity(8L, 7L, 7L, "Richmond Hill");
		TreeEntity ent9 = new TreeEntity(18L, 8L, 7L, "Markham");
		TreeEntity entt = new TreeEntity(100L, null, 100L, "Aurau");
		List<TreeEntity> list = new ArrayList<TreeEntity>();
		list.add(ent3);
		list.add(ent4);
		list.add(ent5);
		list.add(ent6);
		list.add(ent0);
		list.add(ent1);
		list.add(ent2);
		list.add(ent8);
		list.add(ent7);
		list.add(entt);
		list.add(ent9);

		List<TreeEntity> forest = generateForest(list);
		forest = generateNodesLevel(forest);
		List<Long> ids = new ArrayList<Long>();
		for (TreeEntity tree : forest) {
			ids.addAll(traverseTree(tree));
			System.out.println("done a tree");
		}
		TreeEntity tree = getCurrentTree(forest, 0L);
		List<Long> parentIds = new ArrayList<Long>();
		parentIds = getAncestorIds(tree, 2L);
		System.out.println("parent ids size =" + parentIds.size());
		tree = generateNodesLevel(tree);
		List<Long> juniorIds = getJuniorIdsByCurrentId(tree, 0L);
		// List<Long> juniorIds = getJuniorIdsByCurrentId(
		// getCurrentTree(forest, 0L), 1L);

		System.out.println("forest size =" + forest.size());
		System.out.println("juniorIds size =" 
				+ ((juniorIds == null || juniorIds.size() == 0) ? 0 : juniorIds.size()));
	}
}
