/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Starkev2TestModule } from '../../../test.module';
import { StarkeEntityMySuffixDeleteDialogComponent } from 'app/entities/starke-entity-my-suffix/starke-entity-my-suffix-delete-dialog.component';
import { StarkeEntityMySuffixService } from 'app/entities/starke-entity-my-suffix/starke-entity-my-suffix.service';

describe('Component Tests', () => {
    describe('StarkeEntityMySuffix Management Delete Component', () => {
        let comp: StarkeEntityMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<StarkeEntityMySuffixDeleteDialogComponent>;
        let service: StarkeEntityMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [StarkeEntityMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(StarkeEntityMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StarkeEntityMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StarkeEntityMySuffixService);
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
