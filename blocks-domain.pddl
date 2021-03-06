;; domain file: blocksworld-domain-complete.pddl
(define (domain blocks-domain)
	(:requirements :strips)
	(:predicates    (clear ?block)
		            (on-table ?block)
					(holding ?hand ?block)
					(on ?blockAbove ?blockBelow)
					(free ?hand)
					(me ?hand)
					(not-me ?hand)
	)
	(:action pickup
		:parameters (?hand ?ob)
		:precondition (and (clear ?ob) (on-table ?ob) (free ?hand) (me ?hand))
		:effect (and (not (clear ?ob))
				     (not (on-table ?ob))
				     (holding ?hand ?ob)
					 (not (free ?hand))
				)
	)
	(:action putdown
		:parameters (?hand ?ob)
		:precondition (and (holding ?hand ?ob)) ;; putdown can also be requested to another agent
		:effect (and (on-table ?ob)
					 (clear ?ob)
					 (not (holding ?hand ?ob))
					 (free ?hand)
				)
	)
	(:action unstack
		:parameters (?hand ?ob ?y)
		:precondition (and (on ?ob ?Y) (clear ?ob) (free ?hand) (me ?hand))
		:effect (and (not (on ?ob ?y))
		             (not (clear ?ob))
					 (clear ?y)
					 (not (free ?hand))
					 (holding ?hand ?ob)
				)
	)
	(:action stack
		:parameters (?hand ?ob ?y)
		:precondition (and (holding ?hand ?ob) (clear ?y) (me ?hand))  ;; stack can also be requested to another agent
		:effect (and (on ?ob ?y)
					 (clear ?ob)
		             (not (clear ?y))
					 (not (holding ?hand ?ob))
					 (free ?hand)
				)
	)
)