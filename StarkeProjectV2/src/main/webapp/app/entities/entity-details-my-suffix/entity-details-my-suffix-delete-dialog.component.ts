import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEntityDetailsMySuffix } from 'app/shared/model/entity-details-my-suffix.model';
import { EntityDetailsMySuffixService } from './entity-details-my-suffix.service';

@Component({
    selector: 'jhi-entity-details-my-suffix-delete-dialog',
    templateUrl: './entity-details-my-suffix-delete-dialog.component.html'
})
export class EntityDetailsMySuffixDeleteDialogComponent {
    entityDetails: IEntityDetailsMySuffix;

    constructor(
        private entityDetailsService: EntityDetailsMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.entityDetailsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'entityDetailsListModification',
                content: 'Deleted an entityDetails'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-entity-details-my-suffix-delete-popup',
    template: ''
})
export class EntityDetailsMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ entityDetails }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EntityDetailsMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.entityDetails = entityDetails;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
