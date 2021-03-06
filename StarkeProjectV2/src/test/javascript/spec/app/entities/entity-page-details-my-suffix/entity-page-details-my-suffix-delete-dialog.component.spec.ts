/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Starkev2TestModule } from '../../../test.module';
import { EntityPageDetailsMySuffixDeleteDialogComponent } from 'app/entities/entity-page-details-my-suffix/entity-page-details-my-suffix-delete-dialog.component';
import { EntityPageDetailsMySuffixService } from 'app/entities/entity-page-details-my-suffix/entity-page-details-my-suffix.service';

describe('Component Tests', () => {
    describe('EntityPageDetailsMySuffix Management Delete Component', () => {
        let comp: EntityPageDetailsMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<EntityPageDetailsMySuffixDeleteDialogComponent>;
        let service: EntityPageDetailsMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [EntityPageDetailsMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(EntityPageDetailsMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EntityPageDetailsMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityPageDetailsMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
