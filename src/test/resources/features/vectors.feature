Feature: Tuples, Vectors, and Points

  Scenario: p is is a point
    Given p ← point(4.3, -4.2, 3.1)
    Then p.x = 4.3
     And p.y = -4.2
     And p.z = 3.1

  Scenario: v is a vector
    Given v ← vector(4.3, -4.2, 3.1)
    Then v.x = 4.3
     And v.y = -4.2
     And v.z = 3.1

  Scenario: Adding two vectors
    Given v1 ← vector(3, -2, 5)
    And v2 ← vector(-2, 3, 1)
    Then v1 + v2 = vector(1, 1, 6)

  Scenario: Adding a vector and a point
    Given v ← vector(3, -2, 5)
    And p ← point(-2, 3, 1)
    Then v + p = point(1, 1, 6)

  Scenario: Subtracting two points
    Given p1 ← point(3, 2, 1)
    And p2 ← point(5, 6, 7)
    Then p1 - p2 = vector(-2, -4, -6)

  Scenario: Subtracting a vector from a point
    Given p ← point(3, 2, 1)
    And v ← vector(5, 6, 7)
    Then p - v = point(-2, -4, -6)

  Scenario: Subtracting two vectors
    Given v1 ← vector(3, 2, 1)
    And v2 ← vector(5, 6, 7)
    Then v1 - v2 = vector(-2, -4, -6)

  Scenario: Subtracting a vector from the zero vector
    Given zero ← vector(0, 0, 0)
    And v ← vector(1, -2, 3)
    Then zero - v = vector(-1, 2, -3)

  Scenario: Negating a vector
    Given v ← vector(1, -2, 3)
    Then -v = vector(-1, 2, -3)

  Scenario: Negating a point
    Given p ← point(1, -2, 3)
    Then -p = point(-1, 2, -3)

  Scenario: Multiplying a vector by a scalar
    Given v ← vector(1, -2, 3)
    Then v * 3.5 = vector(3.5, -7, 10.5)

  Scenario: Multiplying a point by a scalar
    Given p ← point(1, -2, 3)
    Then p * 3.5 = point(3.5, -7, 10.5)

  Scenario: Multiplying a vector by a fraction
    Given v ← vector(1, -2, 3)
    Then v * 0.5 = vector(0.5, -1, 1.5)

  Scenario: Multiplying a point by a fraction
    Given p ← point(1, -2, 3)
    Then p * 0.5 = point(0.5, -1, 1.5)

  Scenario: Dividing a vector by a scalar
    Given v ← vector(1, -2, 3)
    Then v / 2 = vector(0.5, -1, 1.5)

  Scenario: Dividing a point by a scalar
    Given p ← point(1, -2, 3)
    Then p / 2 = point(0.5, -1, 1.5)

  Scenario: Computing the magnitude of vector(1, 0, 0)
    Given v ← vector(1, 0, 0)
    Then v.magnitude = 1

  Scenario: Computing the magnitude of vector(0, 1, 0)
    Given v ← vector(0, 1, 0)
    Then v.magnitude = 1

  Scenario: Computing the magnitude of vector(0, 0, 1)
    Given v ← vector(0, 0, 1)
    Then v.magnitude = 1

  Scenario: Computing the magnitude of vector(1, 2, 3)
    Given v ← vector(1, 2, 3)
    Then v.magnitude = √14

  Scenario: Computing the magnitude of vector(-1, -2, -3)
    Given v ← vector(-1, -2, -3)
    Then v.magnitude = √14

  Scenario: Normalizing vector(4, 0, 0) gives (1, 0, 0)
    Given v ← vector(4, 0, 0)
    Then v.normalize = vector(1, 0, 0)

  Scenario: Normalizing vector(1, 2, 3)
    Given v ← vector(1, 2, 3)
                                  # vector(1/√14,   2/√14,   3/√14)
    Then v.normalize = approximately vector(0.26726, 0.53452, 0.80178)

  Scenario: The magnitude of a normalized vector
    Given v ← vector(1, 2, 3)
    When norm ← v.normalize
    Then norm.magnitude = 1

  Scenario: The dot product of two vectors
    Given v1 ← vector(1, 2, 3)
    And v2 ← vector(2, 3, 4)
    Then dot(v1, v2) = 20

  Scenario: The cross product of two vectors
    Given v1 ← vector(1, 2, 3)
    And v2 ← vector(2, 3, 4)
    Then cross(v1, v2) = vector(-1, 2, -1)
    And cross(v2, v1) = vector(1, -2, 1)

  Scenario: Colors are (red, green, blue) tuples
    Given c ← color(-0.5, 0.4, 1.7)
    Then c.red = -0.5
    And c.green = 0.4
    And c.blue = 1.7

  Scenario: Adding colors
    Given c1 ← color(0.9, 0.6, 0.75)
    And c2 ← color(0.7, 0.1, 0.25)
    Then c1 + c2 = color(1.6, 0.7, 1.0)

  Scenario: Subtracting colors
    Given c1 ← color(0.9, 0.6, 0.75)
    And c2 ← color(0.7, 0.1, 0.25)
    Then c1 - c2 = color(0.2, 0.5, 0.5)

  Scenario: Multiplying a color by a scalar
    Given c ← color(0.2, 0.3, 0.4)
    Then c * 2 = color(0.4, 0.6, 0.8)

  Scenario: Multiplying colors
    Given c1 ← color(1, 0.2, 0.4)
    And c2 ← color(0.9, 1, 0.1)
    Then c1 * c2 = color(0.9, 0.2, 0.04)

  Scenario: Reflecting a vector approaching at 45°
    Given v ← vector(1, -1, 0)
    And n ← vector(0, 1, 0)
    When r ← reflect(v, n)
    Then r = vector(1, 1, 0)

  Scenario: Reflecting a vector off a slanted surface
    Given v ← vector(0, -1, 0)
    And n ← vector(√2/2, √2/2, 0)
    When r ← reflect(v, n)
    Then r = vector(1, 0, 0)
