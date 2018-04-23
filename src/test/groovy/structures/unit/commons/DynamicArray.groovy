package structures.unit.commons

import structures.vectors.ArrayList
import structures.vectors.ArrayQueue
import structures.vectors.ArrayStack
import test.Spec

abstract class DynamicArraySpec extends Spec {

}

final class DynamicArraySpec_ArrayList extends DynamicArraySpec {

    def setup() {
        myClass = ArrayList
    }
}

final class DynamicArraySpec_ArrayStack extends DynamicArraySpec {

    def setup() {
        myClass = ArrayStack
    }
}

final class DynamicArraySpec_ArrayQueue extends DynamicArraySpec {

    def setup() {
        myClass = ArrayQueue
    }
}