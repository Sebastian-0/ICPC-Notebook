# Used to efficiently build large sets and verify 
# which set a node belongs to 
class UnionFindNode:
  def __init__(self):
    self.rank = 1
    self.parent = self

  def find(self):
    if self.parent != self:
      self.parent = self.parent.find()
    return self.parent

  def union(self, other):
    n1 = self.find()
    n2 = other.find()
    if n1 == n2:
      return
    if n1.rank > n2.rank:
      n2.parent = n1
    elif n1.rank < n2.rank:
      n1.parent = n2
    else:
      n1.parent = n2
      n2.rank += 1